package org.nelect.timestamper.internal.agent;

/**
 * Created by Michael on 2016/6/7.
 */
public interface ConfirmationListener {

    void onConfirm(String transactionId, int nConfirmations, boolean confident);

    void onError(Throwable cause);
}
