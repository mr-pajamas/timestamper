package org.nelect.timestamper.internal.commands;

import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.codec.binary.Hex;
import org.nelect.timestamper.*;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.agent.ConfirmationListener;
import org.nelect.timestamper.internal.agent.TimestampAgent;
import org.nelect.timestamper.internal.persistence.*;

/**
 * Created by Michael on 2016/7/3.
 */
@Privileged(VerifiedPrincipal.class)
public class TimestampCertificateCommand implements Command<Certificate> {

    @NotNull(message = "存证信息不可为空")
    @Valid
    private CertificateInput input;

    public TimestampCertificateCommand(CertificateInput input) {
        this.input = input;
    }

    @Override
    public Certificate doExecute(final CommandContext context) throws TimestamperException {

        VerifiedPrincipal verifiedPrincipal = (VerifiedPrincipal) context.getPrincipal();

        byte[] digest = CertificateDigester.digest(verifiedPrincipal.getName(), input);

        TimestampAgent agent = context.getComponent(TimestampAgent.class);
        String transactionId = agent.timestamp(digest, new ConfirmationListener() {

            @Override
            public void onConfirm(final String transactionId, final int nConfirmations, final boolean confident) {
                context.getExecutor().execute(new Command<Void>() {

                    @Override
                    public Void doExecute(CommandContext context) throws TimestamperException {
                        if (nConfirmations == 1 || confident) {
                            CertificateManager certificateManager = context.getPersistenceContext().getCertificateManager();
                            CertificateEntity entity = certificateManager.getByTransactionId(transactionId);
                            CertificateUpdater updater = certificateManager.newUpdater(entity);
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

        CertificateManager certificateManager = context.getPersistenceContext().getCertificateManager();
        CertificateEntity certificateEntity = certificateManager.newUpdater()
            .setTitle(input.getTitle())
            .setPrincipalId(context.getPrincipal().getId())
//            .setAttachmentName(input.getAttachment().getName())
//            .setAttachmentContentType(input.getAttachment().getContentType())
//            .setAttachmentPath(input.getAttachment().getFile().getAbsolutePath())
            .setAttachmentId(input.getAttachmentId())
            .setDigest(Hex.encodeHexString(digest))
            .setTransactionId(transactionId)
            .save();

        return new CertificateImpl(certificateEntity, verifiedPrincipal.getName());
    }
}
