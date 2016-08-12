package org.nelect.timestamper.partner;

import org.nelect.timestamper.TimestamperException;

/**
 * Created by Michael on 2016/6/10.
 */
public class EInvoiceNotFoundException extends TimestamperException {

    public EInvoiceNotFoundException() {
        super("指定的电子发票信息不存在");
    }
}
