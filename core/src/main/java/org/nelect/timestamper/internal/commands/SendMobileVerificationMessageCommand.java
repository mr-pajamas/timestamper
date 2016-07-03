package org.nelect.timestamper.internal.commands;

import java.text.MessageFormat;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.Command;
import org.nelect.timestamper.internal.CommandContext;
import org.nelect.timestamper.internal.interceptors.TransactionInterceptor;
import org.nelect.timestamper.internal.persistence.*;
import org.nelect.timestamper.internal.sms.MessageSender;
import org.nelect.timestamper.user.VerificationCodeHash;

/**
 * Created by Michael on 2016/7/1.
 */
@TransactionInterceptor.Transactional
public class SendMobileVerificationMessageCommand implements Command<VerificationCodeHash> {

    private static final int TTL = 300;

    private Seconds ttlSeconds = Seconds.seconds(TTL);

    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$", message = "非法的手机号")
    private String mobile;

    public SendMobileVerificationMessageCommand(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public VerificationCodeHash doExecute(CommandContext context) throws TimestamperException {
        String code = generateCode();

        MobileVerificationManager manager = context.getPersistenceContext().getMobileVerificationManager();

        MobileVerificationEntity entity = manager.getByMobile(mobile);

        MobileVerificationUpdater updater;
        if (entity == null)
            updater = manager.newUpdater();
        else
            updater = manager.newUpdater(entity);

        entity = updater
            .setMobile(mobile)
            .setCode(code)
            .setExpiration(new DateTime().plus(ttlSeconds).toDate())
            .setUsed(false)
            .save();

        MessageSender messageSender = context.getComponent(MessageSender.class);

        String message = MessageFormat.format("验证码：{0}。您正在验证您的手机，验证码{1}分钟内有效",
            code, ttlSeconds.toStandardMinutes().getMinutes());

        messageSender.batchSendAsync(message, mobile);

        return new VerificationCodeHashImpl(entity);
    }

    private String generateCode() {
        return RandomStringUtils.randomNumeric(6);
    }
}
