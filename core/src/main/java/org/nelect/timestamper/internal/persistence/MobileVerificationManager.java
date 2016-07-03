package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/2.
 */
public interface MobileVerificationManager extends EntityManager<String, MobileVerificationEntity, MobileVerificationUpdater, Query<MobileVerificationEntity>> {

    MobileVerificationEntity getByMobile(String mobile);
}
