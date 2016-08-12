package org.nelect.timestamper.internal;

import org.nelect.timestamper.internal.persistence.UserEntity;

/**
 * Created by Michael on 2016/7/7.
 */
class Verified {

    private UserEntity userEntity;

    Verified(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    int getCertificateCount() {
        return userEntity.getCertificateCount() == null ? 0 : userEntity.getCertificateCount();
    }

    int getBalance() {
        return userEntity.getBalance() == null ? 0 : userEntity.getBalance();
    }
}
