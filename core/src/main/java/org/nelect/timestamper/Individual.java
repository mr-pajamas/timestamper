package org.nelect.timestamper;

/**
 * Created by Michael on 2016/7/3.
 */
public interface Individual extends VerifiedPrincipal {

    String getIdCardNumber();

    Attachment getIdCardFrontPicture();

    Attachment getIdCardBackPicture();
}
