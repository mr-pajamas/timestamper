package org.nelect.timestamper.server;

/**
 * Created by Michael on 2016/6/4.
 */
public interface Timestamper {

    String timestamp(byte[] data, int confirmationThreshold, ConfirmationHandler confirmationHandler);

    String timestamp(TimestampRequest request, ConfirmationHandler confirmationHandler);

    interface ConfirmationHandler {
        void onNConfirmationsChange(String txId, int nConfirmations);
    }
}
