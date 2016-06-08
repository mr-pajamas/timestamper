package org.nelect.timestamper.server;

/**
 * Created by Michael on 2016/6/6.
 */
public class TimestampRequest {

    private byte[] data;
    private int confirmationThreshold;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getConfirmationThreshold() {
        return confirmationThreshold;
    }

    public void setConfirmationThreshold(int confirmationThreshold) {
        this.confirmationThreshold = confirmationThreshold;
    }
}
