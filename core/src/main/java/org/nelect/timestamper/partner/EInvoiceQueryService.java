package org.nelect.timestamper.partner;

/**
 * Created by Michael on 2016/5/30.
 */
public interface EInvoiceQueryService {

    EInvoice getInvoiceByCheckId(String checkId);

    EInvoice getInvoiceBySizeAndChecksum(long size, String checksum);
}
