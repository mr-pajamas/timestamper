package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/8/12.
 */
public interface EInvoiceManager extends EntityManager<String, EInvoiceEntity, EInvoiceUpdater, EInvoiceQuery> {

    EInvoiceEntity getByTransactionId(String transactionId);

    EInvoiceEntity getByDigest(String digest);

    EInvoiceEntity getByCheckId(String checkId);

    EInvoiceEntity getByCertNumber(String certNumber);

    EInvoiceEntity getByChecksum(String checksum);
}
