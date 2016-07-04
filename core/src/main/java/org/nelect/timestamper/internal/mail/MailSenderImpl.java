package org.nelect.timestamper.internal.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
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
    public void send(final Mail mail) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mail.setupMimeMessage(mimeMessage);
            }
        };

        mailSender.send(preparator);
    }

    @Override
    @Async
    public void sendAsync(Mail mail) {
        send(mail);
    }
}
