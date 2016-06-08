package org.nelect.timestamper.server;

/**
 * Created by Michael on 2016/6/6.
 */
public class TimestampResponse {

    private String transactionId;
    private int nConfirmations;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getnConfirmations() {
        return nConfirmations;
    }

    public void setnConfirmations(int nConfirmations) {
        this.nConfirmations = nConfirmations;
    }
}
