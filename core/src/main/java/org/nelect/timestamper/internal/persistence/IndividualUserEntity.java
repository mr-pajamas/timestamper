package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/5.
 */
public interface IndividualUserEntity extends UserEntity {

    String getName();

    String getIdCardNumber();

    String getIdCardFrontPictureContentType();

    String getIdCardFrontPicturePath();

    String getIdCardBackPictureContentType();

    String getIdCardBackPicturePath();
}
