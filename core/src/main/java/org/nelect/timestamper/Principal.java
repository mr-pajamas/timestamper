package org.nelect.timestamper;

/**
 * Created by Michael on 2016/5/30.
 */
public interface Principal {

    String getId();

    String getEmail();

    //boolean isEmailVerified();

    String getMobile();

    boolean isIdentityVerified();
}
