package org.nelect.timestamper.internal.persistence.hibernate;

import org.nelect.timestamper.internal.persistence.OrganizationInfoEntity;

/**
 * Created by Michael on 2016/7/12.
 */
class OrganizationInfoEntityImpl implements OrganizationInfoEntity {

    private String userId;
    //private UserEntityImpl userEntity;
    private String name;
    private String registrationNumber;
/*
    private String businessLicensePictureContentType;
    private String businessLicensePicturePath;
*/
    private String businessLicensePicture;

    @Override
    public String getId() {
        return userId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

/*
    @Override
    public String getBusinessLicensePictureContentType() {
        return businessLicensePictureContentType;
    }

    public void setBusinessLicensePictureContentType(String businessLicensePictureContentType) {
        this.businessLicensePictureContentType = businessLicensePictureContentType;
    }

    @Override
    public String getBusinessLicensePicturePath() {
        return businessLicensePicturePath;
    }

    public void setBusinessLicensePicturePath(String businessLicensePicturePath) {
        this.businessLicensePicturePath = businessLicensePicturePath;
    }
*/

    @Override
    public String getBusinessLicensePicture() {
        return businessLicensePicture;
    }

    public void setBusinessLicensePicture(String businessLicensePicture) {
        this.businessLicensePicture = businessLicensePicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationInfoEntityImpl that = (OrganizationInfoEntityImpl) o;

        if (!userId.equals(that.userId)) return false;
        if (!name.equals(that.name)) return false;
        if (!registrationNumber.equals(that.registrationNumber)) return false;
        return businessLicensePicture.equals(that.businessLicensePicture);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + registrationNumber.hashCode();
        result = 31 * result + businessLicensePicture.hashCode();
        return result;
    }
}
