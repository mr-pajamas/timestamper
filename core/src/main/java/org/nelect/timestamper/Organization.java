package org.nelect.timestamper;

/**
 * Created by Michael on 2016/7/3.
 */
public interface Organization extends VerifiedPrincipal {

    String getRegistrationNumber();

    Attachment getBusinessLicensePicture();
}
