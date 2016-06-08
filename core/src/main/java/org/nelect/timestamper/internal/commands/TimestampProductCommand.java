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
 * Created by Michael on 2016/6/7.
 */
public class TimestampProductCommand implements Command<CreditInfo> {

    @NotNull(message = "存证信息不可为空")
    @Valid
    private CreditInfoInput input;

    public TimestampProductCommand(CreditInfoInput input) {
        this.input = input;
    }

    @Override
    public CreditInfo doExecute(final CommandContext context) throws TimestamperException {

        // 运算digest并上传到timestamper服务器
        byte[] digest = CreditInfoDigester.digest(input);

        TimestampAgent agent = context.getTimestampAgent();
        String transactionId = agent.timestamp(digest, new ConfirmationListener() {

            @Override
            public void onConfirm(String transactionId, int nConfirmations, boolean confident) {
                if (nConfirmations == 1 || confident) {
                    CreditInfoManager entityManager = context.getPersistenceContext().getCreditInfoManager();
                    CreditInfoEntity entity = entityManager.getByTransactionId(transactionId);
                    CreditInfoUpdater updater = entityManager.newUpdater(entity);
                    if (nConfirmations == 1) updater.setRegistrationTime(new Date());
                    updater.setConfident(confident);
                    updater.save();
                }
            }

            @Override
            public void onError(Throwable cause) {

            }
        });

        // 存储到本地数据库
        CreditInfoManager creditInfoManager = context.getPersistenceContext().getCreditInfoManager();
        CreditInfoEntity creditInfoEntity = creditInfoManager.newUpdater()
            .setType(CreditInfoEntityType.PRODUCT)
            .setCheckId(input.getCheckId())
            .setName(input.getName())
            .setPrincipal(input.getPrincipalName())
            .setAttachmentName(input.getAttachment().getName())
            .setAttachmentContentType(input.getAttachment().getContentType())
            .setAttachmentPath(input.getAttachment().getFile().getAbsolutePath())
            .setDigest(Hex.encodeHexString(digest))
            .setDetails(input.getDetails())
            .setTransactionId(transactionId)
            .save();

        return new CreditInfoImpl(creditInfoEntity);
    }
}
