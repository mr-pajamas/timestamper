package org.nelect.timestamper.internal.commands;

import java.util.Map;
import java.util.Properties;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.Command;
import org.nelect.timestamper.internal.CommandContext;
import org.nelect.timestamper.internal.interceptors.TransactionInterceptor;
import org.nelect.timestamper.internal.mail.Mail;
import org.nelect.timestamper.internal.mail.MailSender;
import org.nelect.timestamper.internal.persistence.*;
import org.nelect.timestamper.user.VerificationCodeHash;
import org.springframework.ui.velocity.VelocityEngineUtils;

import static org.apache.commons.lang3.StringUtils.trimToNull;

/**
 * Created by Michael on 2016/7/3.
 */
@TransactionInterceptor.Transactional
public class SendEmailVerificationMessageCommand implements Command<VerificationCodeHash> {

    private static final int TTL = 300;

    private Seconds ttlSeconds = Seconds.seconds(TTL);

    @NotBlank(message = "Email不能为空")
    @Email(message = "非法的Email地址")
    private String email;

    public SendEmailVerificationMessageCommand(String email) {
        this.email = trimToNull(email);
    }

    @Override
    public VerificationCodeHash doExecute(CommandContext context) throws TimestamperException {
        String code = generateCode();

        EmailVerificationManager manager = context.getPersistenceContext().getEmailVerificationManager();

        EmailVerificationEntity entity = manager.getByEmail(email);

        EmailVerificationUpdater updater;
        if (entity == null)
            updater = manager.newUpdater();
        else
            updater = manager.newUpdater(entity);

        entity = updater
            .setEmail(email)
            .setCode(code)
            .setExpiration(new DateTime().plus(ttlSeconds).toDate())
            .setUsed(false)
            .save();

        MailSender mailSender = context.getComponent(MailSender.class);

        Map<String, Object> templateModel = ImmutableMap.<String, Object> builder()
            .put("code", entity.getCode())
            .build();

        String message = VelocityEngineUtils.mergeTemplateIntoString(
            context.getComponent(VelocityEngine.class), "org/nelect/timestamper/internal/email-verification.vm", "UTF-8", templateModel);

        Properties config = context.getConfig();

        Mail mail = new Mail.Builder()
            .from(config.getProperty("mail.from"))
            .fromName("存证通")
            .subject("[存证通] 验证您的邮箱")
            .tos(email)
            .text(message)
            .html(true)
            .build();

        mailSender.sendAsync(mail);

        return new VerificationCodeHashImpl(entity);
    }

    private String generateCode() {
        return RandomStringUtils.randomNumeric(6);
    }
}
