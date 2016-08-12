package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/9.
 */
public interface OrganizationInfoUpdater extends Updater<OrganizationInfoEntity> {

    OrganizationInfoUpdater setUserId(String userId);

    OrganizationInfoUpdater setName(String name);

    OrganizationInfoUpdater setRegistrationNumber(String registrationNumber);
/*
    OrganizationInfoUpdater setBusinessLicensePictureContentType(String businessLicensePictureContentType);

    OrganizationInfoUpdater setBusinessLicensePicturePath(String businessLicensePicturePath);
    */

    OrganizationInfoUpdater setBusinessLicensePicture(String businessLicensePicture);
}
