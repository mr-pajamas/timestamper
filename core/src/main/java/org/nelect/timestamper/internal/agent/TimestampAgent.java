package org.nelect.timestamper.internal.agent;

/**
 * Created by Michael on 2016/6/7.
 */
public interface TimestampAgent {

    String timestamp(byte[] digest, ConfirmationListener listener);

    String timestamp(byte[] digest);
}
