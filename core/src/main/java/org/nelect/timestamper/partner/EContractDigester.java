package org.nelect.timestamper.partner;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Michael on 2016/6/10.
 */
public class EContractDigester {

    public static byte[] digest(EContractInput input) {
        String principalName = input.getPrincipalName();
        String certNumber = input.getCertNumber();
        String checksum = input.getChecksum();

        return DigestUtils.sha1(principalName + certNumber + checksum);
    }
}
