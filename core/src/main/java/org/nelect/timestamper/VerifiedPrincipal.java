package org.nelect.timestamper;

/**
 * Created by Michael on 2016/7/3.
 */
public interface VerifiedPrincipal extends Principal {

    String getName();

    int getCertificateCount();

    int getBalance();
}
