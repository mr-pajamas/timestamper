package org.nelect.timestamper.internal.commands;

import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.codec.binary.Hex;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.agent.ConfirmationListener;
import org.nelect.timestamper.internal.agent.TimestampAgent;
import org.nelect.timestamper.internal.persistence.*;
import org.nelect.timestamper.partner.*;

/**
 * Created by Michael on 2016/6/10.
 */
public class TimestampEContractCommand implements Command<EContract> {

    @NotNull(message = "存证信息不可为空")
    @Valid
    private EContractInput input;

    public TimestampEContractCommand(EContractInput input) {
        this.input = input;
    }

    @Override
    public EContract doExecute(final CommandContext context) throws TimestamperException {

        byte[] digest = EContractDigester.digest(input);

        TimestampAgent agent = context.getTimestampAgent();
        String transactionId = agent.timestamp(digest, new ConfirmationListener() {

            @Override
            public void onConfirm(final String transactionId, final int nConfirmations, final boolean confident) {
                context.getExecutor().execute(new Command<Void>() {

                    @Override
                    public Void doExecute(CommandContext context) throws TimestamperException {
                        if (nConfirmations == 1 || confident) {
                            EContractManager entityManager = context.getPersistenceContext().getEContractManager();
                            EContractEntity entity = entityManager.getByTransactionId(transactionId);
                            EContractUpdater updater = entityManager.newUpdater(entity);
                            if (nConfirmations == 1) updater.setRegistrationTime(new Date());
                            updater.setConfident(confident);
                            updater.save();
                        }
                        return null;
                    }
                }, context.getContextFactory().newCommandContext());
            }

            @Override
            public void onError(Throwable cause) {

            }
        });

        EContractManager eContractManager = context.getPersistenceContext().getEContractManager();
        EContractEntity eContractEntity = eContractManager.newUpdater()
            .setCheckId(input.getCheckId())
            .setPrincipal(input.getPrincipalName())
            .setCertNumber(input.getCertNumber())
            .setChecksum(input.getChecksum())
            .setDigest(Hex.encodeHexString(digest))
            .setTransactionId(transactionId)
            .save();

        return new EContractImpl(eContractEntity);
    }
}
