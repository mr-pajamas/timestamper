package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/5/30.
 */
public interface CreditInfoManager extends EntityManager<String, CreditInfoEntity, CreditInfoUpdater, CreditInfoQuery> {

    CreditInfoEntity getByTransactionId(String transactionId);
}
