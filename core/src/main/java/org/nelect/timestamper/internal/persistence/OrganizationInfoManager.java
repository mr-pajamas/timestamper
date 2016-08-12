package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/9.
 */
public interface OrganizationInfoManager extends EntityManager<String, OrganizationInfoEntity, OrganizationInfoUpdater, OrganizationInfoQuery> {

    OrganizationInfoEntity getByUserId(String userId);

    OrganizationInfoEntity getByRegistrationNumber(String registrationNumber);
}
