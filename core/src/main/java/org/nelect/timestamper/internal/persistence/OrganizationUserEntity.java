package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/5.
 */
public interface OrganizationUserEntity extends UserEntity {

    String getName();

    String getRegistrationNumber();

    String getBusinessLicensePictureContentType();

    String getBusinessLicensePicturePath();
}
