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
public class TimestampEInvoiceCommand implements Command<EInvoice> {

    @NotNull(message = "存证信息不可为空")
    @Valid
    private EInvoiceInput input;

    public TimestampEInvoiceCommand(EInvoiceInput input) {
        this.input = input;
    }

    @Override
    public EInvoice doExecute(final CommandContext context) throws TimestamperException {

        byte[] digest = EInvoiceDigester.digest(input);

        TimestampAgent agent = context.getComponent(TimestampAgent.class);
        String transactionId = agent.timestamp(digest, new ConfirmationListener() {

            @Override
            public void onConfirm(final String transactionId, final int nConfirmations, final boolean confident) {
                context.getExecutor().execute(new Command<Void>() {

                    @Override
                    public Void doExecute(CommandContext context) throws TimestamperException {
                        if (nConfirmations == 1 || confident) {
                            EInvoiceManager entityManager = context.getPersistenceContext().getEInvoiceManager();
                            EInvoiceEntity entity = entityManager.getByTransactionId(transactionId);
                            EInvoiceUpdater updater = entityManager.newUpdater(entity);
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

        EInvoiceManager eInvoiceManager = context.getPersistenceContext().getEInvoiceManager();
        EInvoiceEntity eInvoiceEntity = eInvoiceManager.newUpdater()
            .setCheckId(input.getCheckId())
            .setPrincipal(input.getPrincipalName())
            .setCertNumber(input.getCertNumber())
            .setChecksum(input.getChecksum())
            .setDigest(Hex.encodeHexString(digest))
            .setTransactionId(transactionId)
            .save();

        return new EInvoiceImpl(eInvoiceEntity);
    }
}
