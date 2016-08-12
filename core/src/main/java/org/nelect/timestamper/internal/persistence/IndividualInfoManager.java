package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/9.
 */
public interface IndividualInfoManager extends EntityManager<String, IndividualInfoEntity, IndividualInfoUpdater, IndividualInfoQuery> {

    IndividualInfoEntity getByUserId(String userId);

    IndividualInfoEntity getByIdCardNumber(String idCardNumber);
}
