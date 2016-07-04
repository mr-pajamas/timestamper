package org.nelect.timestamper.internal.mail;

/**
 * Created by Michael on 2016/7/2.
 */
public interface MailSender {

    void send(Mail mail);

    void sendAsync(Mail mail);
}
