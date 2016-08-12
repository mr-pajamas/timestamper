package org.nelect.timestamper.server;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

import org.bitcoinj.core.*;
import org.bitcoinj.core.listeners.BlocksDownloadedEventListener;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.utils.BriefLogFormatter;
import org.bitcoinj.wallet.SendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Michael on 2016/7/18.
 */
public class TimestamperImpl implements Timestamper {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private NetworkParameters params;
    private WalletAppKit kit;
    private ProofStore proofStore;

    public TimestamperImpl(boolean useTestNet, File directory, String prefix) {
        BriefLogFormatter.init();
        if (useTestNet) {
            params = TestNet3Params.get();
        } else {
            params = MainNetParams.get();
        }
        kit = new WalletAppKit(params, directory, prefix);
        proofStore = new ProofStore();

        kit.startAsync();
        kit.awaitRunning();

        logger.info("比特币钱包客户端已经同步");
        logger.info("钱包接收地址：{}", kit.wallet().currentReceiveAddress());

        kit.peerGroup().addBlocksDownloadedEventListener(new BlocksDownloadedEventListener() {

            @Override
            public void onBlocksDownloaded(Peer peer, Block block, @Nullable FilteredBlock filteredBlock, int blocksLeft) {
                if (filteredBlock != null) {
                    List<Proof> pendingProofs = proofStore.getPendingProofs();
                    if (!pendingProofs.isEmpty()) {

                        List<Sha256Hash> hashes = new LinkedList<>();
                        PartialMerkleTree tree = filteredBlock.getPartialMerkleTree();
                        tree.getTxnHashAndMerkleRoot(hashes);

                        for (Proof proof : pendingProofs) {
                            if (hashes.contains(proof.getTransactionHash())) {
                                proof.setMerkleTreePayload(tree.bitcoinSerialize());
                                proof.setBlockHash(filteredBlock.getHash());
                                proofStore.save(proof);
                            }
                        }

                        proofStore.flush();
                    }
                }
            }
        });
    }

    @Override
    public String timestamp(byte[] data, final int confirmationThreshold, final ConfirmationHandler confirmationHandler) {

        try {
            Transaction tx = new Transaction(params);
            tx.addOutput(Coin.ZERO, ScriptBuilder.createOpReturnScript(data));
            kit.wallet().sendCoins(SendRequest.forTx(tx));

            Proof proof = new Proof(tx.bitcoinSerialize());
            proofStore.saveAndFlush(proof);

            tx.getConfidence().addEventListener(new TransactionConfidence.Listener() {

                @Override
                public void onConfidenceChanged(TransactionConfidence confidence, ChangeReason reason) {
                    if (confidence.getConfidenceType() != TransactionConfidence.ConfidenceType.BUILDING)
                        return;
                    String txId = confidence.getTransactionHash().toString();
                    int nConfirmations = confidence.getDepthInBlocks();
                    if (nConfirmations >= confirmationThreshold) {
                        confidence.removeEventListener(this);
                        /*
                        Proof proof = proofStore.get(txId);
                        proof.setConfident(true);
                        proofStore.saveAndFlush(proof);
                        */
                    }
                    try {
                        confirmationHandler.onNConfirmationsChange(txId, nConfirmations);
                    } catch (Exception e) {
                        logger.warn("交易确认进度回调出错", e);
                    }
                }
            });

            return tx.getHashAsString();
        } catch (InsufficientMoneyException ime) {
            throw new ServerError(ime);
        }
    }

    @Override
    public String timestamp(TimestampRequest request, ConfirmationHandler confirmationHandler) {
        return timestamp(request.getData(), request.getConfirmationThreshold(), confirmationHandler);
    }

    public void close() {
        kit.stopAsync();
        kit.awaitTerminated();
        proofStore.close();
    }
}
