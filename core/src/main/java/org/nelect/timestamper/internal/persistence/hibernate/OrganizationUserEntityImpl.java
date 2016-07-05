package org.nelect.timestamper.internal.persistence.hibernate;

import org.nelect.timestamper.internal.persistence.OrganizationUserEntity;

/**
 * Created by Michael on 2016/7/5.
 */
class OrganizationUserEntityImpl extends UserEntityImpl implements OrganizationUserEntity {

    private String name;
    private String registrationNumber;
    private String businessLicensePictureContentType;
    private String businessLicensePicturePath;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrganizationUserEntityImpl that = (OrganizationUserEntityImpl) o;

        if (!name.equals(that.name)) return false;
        if (!registrationNumber.equals(that.registrationNumber)) return false;
        if (!businessLicensePictureContentType.equals(that.businessLicensePictureContentType))
            return false;
        return businessLicensePicturePath.equals(that.businessLicensePicturePath);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + registrationNumber.hashCode();
        result = 31 * result + businessLicensePictureContentType.hashCode();
        result = 31 * result + businessLicensePicturePath.hashCode();
        return result;
    }
}
