package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/3.
 */
public interface CertificateManager extends EntityManager<String, CertificateEntity, CertificateUpdater, CertificateQuery> {

    CertificateEntity getByTransactionId(String transactionId);
}
