package org.nelect.timestamper.partner;

import org.nelect.timestamper.TimestamperException;

/**
 * Created by Michael on 2016/6/10.
 */
public class EInvoiceNotConfidentException extends TimestamperException {

    public EInvoiceNotConfidentException() {
        super("指定的电子发票尚未完成存证");
    }
}
