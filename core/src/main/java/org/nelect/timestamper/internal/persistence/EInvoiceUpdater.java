package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/8/12.
 */
public interface EInvoiceUpdater extends Updater<EInvoiceEntity> {

    EInvoiceUpdater setCheckId(String checkId);

    EInvoiceUpdater setPrincipal(String principal);

    EInvoiceUpdater setCertNumber(String certNumber);

    EInvoiceUpdater setChecksum(String checksum);

    EInvoiceUpdater setDigest(String digest);

    EInvoiceUpdater setTransactionId(String transactionId);

    EInvoiceUpdater setRegistrationTime(Date registrationTime);

    EInvoiceUpdater setConfident(Boolean confident);
}
