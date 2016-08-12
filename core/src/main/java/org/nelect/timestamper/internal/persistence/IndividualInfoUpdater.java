package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/9.
 */
public interface IndividualInfoUpdater extends Updater<IndividualInfoEntity> {

    IndividualInfoUpdater setUserId(String userId);

    IndividualInfoUpdater setName(String name);

    IndividualInfoUpdater setIdCardNumber(String idCardNumber);

/*
    IndividualInfoUpdater setIdCardFrontPictureContentType(String idCardFrontPictureContentType);

    IndividualInfoUpdater setIdCardFrontPicturePath(String idCardFrontPicturePath);
*/
    IndividualInfoUpdater setIdCardFrontPicture(String idCardFrontPicture);

/*    IndividualInfoUpdater setIdCardBackPictureContentType(String idCardBackPictureContentType);

    IndividualInfoUpdater setIdCardBackPicturePath(String idCardBackPicturePath);
    */

    IndividualInfoUpdater setIdCardBackPicture(String idCardBackPicture);
}
