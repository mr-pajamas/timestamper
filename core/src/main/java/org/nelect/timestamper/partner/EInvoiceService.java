package org.nelect.timestamper.partner;

/**
 * Created by Michael on 2016/5/30.
 */
public interface EInvoiceService {

    EInvoice getEInvoiceByCheckId(String checkId);

    EInvoice getEInvoiceByChecksum(String checksum);
}
