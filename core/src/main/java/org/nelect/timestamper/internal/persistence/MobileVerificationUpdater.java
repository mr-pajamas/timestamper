package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/7/2.
 */
public interface MobileVerificationUpdater extends Updater<MobileVerificationEntity> {

    MobileVerificationUpdater setMobile(String mobile);

    MobileVerificationUpdater setCode(String code);

    MobileVerificationUpdater setExpiration(Date expiration);

    MobileVerificationUpdater setUsed(Boolean used);
}
