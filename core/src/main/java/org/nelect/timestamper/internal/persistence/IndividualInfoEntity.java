package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/9.
 */
public interface IndividualInfoEntity extends Entity<String> {

    String getUserId();

    String getName();

    String getIdCardNumber();

/*
    String getIdCardFrontPictureContentType();

    String getIdCardFrontPicturePath();
*/
    String getIdCardFrontPicture();

/*    String getIdCardBackPictureContentType();

    String getIdCardBackPicturePath();
    */
    String getIdCardBackPicture();
}
