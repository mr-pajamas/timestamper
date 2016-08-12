package org.nelect.timestamper.partner;

/**
 * Created by Michael on 2016/5/30.
 */
public interface EInvoiceService {

    EInvoice timestampEInvoice(EInvoiceInput input);

    EInvoice getEInvoice(String digest);

    EInvoice findEInvoiceByCheckId(String checkId);

    EInvoice findEInvoiceByCertNumber(String certNumber);

    EInvoice findEInvoiceByChecksum(String checksum);
}
