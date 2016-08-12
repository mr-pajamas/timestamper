package org.nelect.timestamper.internal.persistence.hibernate;

import org.nelect.timestamper.internal.persistence.IndividualInfoEntity;

/**
 * Created by Michael on 2016/7/9.
 */
class IndividualInfoEntityImpl implements IndividualInfoEntity {

    private String userId;
    //private UserEntityImpl userEntity;
    private String name;
    private String idCardNumber;
//    private String idCardFrontPictureContentType;
//    private String idCardFrontPicturePath;
//    private String idCardBackPictureContentType;
//    private String idCardBackPicturePath;
    private String idCardFrontPicture;
    private String idCardBackPicture;

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
    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

/*
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
*/

    @Override
    public String getIdCardFrontPicture() {
        return idCardFrontPicture;
    }

    public void setIdCardFrontPicture(String idCardFrontPicture) {
        this.idCardFrontPicture = idCardFrontPicture;
    }

    @Override
    public String getIdCardBackPicture() {
        return idCardBackPicture;
    }

    public void setIdCardBackPicture(String idCardBackPicture) {
        this.idCardBackPicture = idCardBackPicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndividualInfoEntityImpl that = (IndividualInfoEntityImpl) o;

        if (!userId.equals(that.userId)) return false;
        if (!name.equals(that.name)) return false;
        if (!idCardNumber.equals(that.idCardNumber)) return false;
        if (!idCardFrontPicture.equals(that.idCardFrontPicture)) return false;
        return idCardBackPicture.equals(that.idCardBackPicture);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + idCardNumber.hashCode();
        result = 31 * result + idCardFrontPicture.hashCode();
        result = 31 * result + idCardBackPicture.hashCode();
        return result;
    }
}
