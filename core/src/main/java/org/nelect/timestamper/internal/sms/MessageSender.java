package org.nelect.timestamper.internal.sms;

/**
 * Created by Michael on 2016/6/28.
 */
public interface MessageSender {

    void batchSend(String message, String mobile, String... otherMobiles);

    void batchSendAsync(String message, String mobile, String... otherMobiles);
}
