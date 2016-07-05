package org.nelect.timestamper.internal.persistence.hibernate;

import org.nelect.timestamper.internal.persistence.IndividualUserEntity;

/**
 * Created by Michael on 2016/7/5.
 */
class IndividualUserEntityImpl extends UserEntityImpl implements IndividualUserEntity {

    private String name;
    private String idCardNumber;
    private String idCardFrontPictureContentType;
    private String idCardFrontPicturePath;
    private String idCardBackPictureContentType;
    private String idCardBackPicturePath;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    @Override
    public String getIdCardFrontPictureContentType() {
        return idCardFrontPictureContentType;
    }

    public void setIdCardFrontPictureContentType(String idCardFrontPictureContentType) {
        this.idCardFrontPictureContentType = idCardFrontPictureContentType;
    }

    @Override
    public String getIdCardFrontPicturePath() {
        return idCardFrontPicturePath;
    }

    public void setIdCardFrontPicturePath(String idCardFrontPicturePath) {
        this.idCardFrontPicturePath = idCardFrontPicturePath;
    }

    @Override
    public String getIdCardBackPictureContentType() {
        return idCardBackPictureContentType;
    }

    public void setIdCardBackPictureContentType(String idCardBackPictureContentType) {
        this.idCardBackPictureContentType = idCardBackPictureContentType;
    }

    @Override
    public String getIdCardBackPicturePath() {
        return idCardBackPicturePath;
    }

    public void setIdCardBackPicturePath(String idCardBackPicturePath) {
        this.idCardBackPicturePath = idCardBackPicturePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        IndividualUserEntityImpl that = (IndividualUserEntityImpl) o;

        if (!name.equals(that.name)) return false;
        if (!idCardNumber.equals(that.idCardNumber)) return false;
        if (!idCardFrontPictureContentType.equals(that.idCardFrontPictureContentType))
            return false;
        if (!idCardFrontPicturePath.equals(that.idCardFrontPicturePath))
            return false;
        if (!idCardBackPictureContentType.equals(that.idCardBackPictureContentType))
            return false;
        return idCardBackPicturePath.equals(that.idCardBackPicturePath);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + idCardNumber.hashCode();
        result = 31 * result + idCardFrontPictureContentType.hashCode();
        result = 31 * result + idCardFrontPicturePath.hashCode();
        result = 31 * result + idCardBackPictureContentType.hashCode();
        result = 31 * result + idCardBackPicturePath.hashCode();
        return result;
    }
}
