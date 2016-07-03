package org.nelect.timestamper.internal.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.*;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by Michael on 2016/7/3.
 */
public class MailSenderImpl implements MailSender {

    private JavaMailSender mailSender;

    public MailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void batchSend(final String message, final boolean html, final String email, final String... otherEmails) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.addTo(email);
                for (String otherEmail : otherEmails) {
                    messageHelper.addTo(otherEmail);
                }
                messageHelper.setFrom("lctang@foxmail.com");
                messageHelper.setSubject("[存证通] 验证您的邮箱");
                messageHelper.setText(message, html);
            }
        };

        mailSender.send(preparator);
    }

    @Override
    @Async
    public void batchSendAsync(String message, boolean html, String email, String... otherEmails) {
        batchSend(message, html, email, otherEmails);
    }
}
