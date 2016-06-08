package org.nelect.timestamper.server;

import java.util.Arrays;

import org.bitcoinj.core.Sha256Hash;

/**
 * Created by Michael on 2016/6/5.
 */
public class Proof {

    String transactionId;
    private byte[] transactionPayload;
    private byte[] merkleTreePayload;
    private String blockHash;
    //private boolean confident;

    Proof() {}

    public Proof(byte[] transactionPayload) {
        this.transactionPayload = transactionPayload;
        transactionId = Sha256Hash.wrapReversed(Sha256Hash.hashTwice(this.transactionPayload)).toString();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Sha256Hash getTransactionHash() {
        return Sha256Hash.wrap(transactionId);
    }

    public byte[] getTransactionPayload() {
        return transactionPayload;
    }

    public void setTransactionPayload(byte[] transactionPayload) {
        this.transactionPayload = transactionPayload;
    }

    public byte[] getMerkleTreePayload() {
        return merkleTreePayload;
    }

    public void setMerkleTreePayload(byte[] merkleTreePayload) {
        this.merkleTreePayload = merkleTreePayload;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public void setBlockHash(Sha256Hash blockHash) {
        this.blockHash = blockHash.toString();
    }

/*
    public boolean isConfident() {
        return confident;
    }

    public void setConfident(boolean confident) {
        this.confident = confident;
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Proof proof = (Proof) o;

        //if (confident != proof.confident) return false;
        if (!transactionId.equals(proof.transactionId)) return false;
        if (!Arrays.equals(transactionPayload, proof.transactionPayload))
            return false;
        if (!Arrays.equals(merkleTreePayload, proof.merkleTreePayload))
            return false;
        return blockHash != null ? blockHash.equals(proof.blockHash) : proof.blockHash == null;

    }

    @Override
    public int hashCode() {
        int result = transactionId.hashCode();
        result = 31 * result + Arrays.hashCode(transactionPayload);
        result = 31 * result + Arrays.hashCode(merkleTreePayload);
        result = 31 * result + (blockHash != null ? blockHash.hashCode() : 0);
        //result = 31 * result + (confident ? 1 : 0);
        return result;
    }
}
