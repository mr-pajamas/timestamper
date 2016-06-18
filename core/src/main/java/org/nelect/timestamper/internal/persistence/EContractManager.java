package org.nelect.timestamper.internal.persistence;


/**
 * Created by Michael on 2016/6/10.
 */
public interface EContractManager extends EntityManager<String, EContractEntity, EContractUpdater, EContractQuery> {

    EContractEntity getByTransactionId(String transactionId);

    EContractEntity getByDigest(String digest);

    EContractEntity getByCheckId(String checkId);

    EContractEntity getByChecksum(String checksum);
}
