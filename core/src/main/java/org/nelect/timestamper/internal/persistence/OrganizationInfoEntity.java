package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/9.
 */
public interface OrganizationInfoEntity extends Entity<String> {

    String getUserId();

    String getName();

    String getRegistrationNumber();
/*
    String getBusinessLicensePictureContentType();

    String getBusinessLicensePicturePath();
    */

    String getBusinessLicensePicture();
}
