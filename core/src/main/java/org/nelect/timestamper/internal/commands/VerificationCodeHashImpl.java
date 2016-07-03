package org.nelect.timestamper.internal.commands;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomUtils;
import org.nelect.timestamper.internal.persistence.EmailVerificationEntity;
import org.nelect.timestamper.internal.persistence.MobileVerificationEntity;
import org.nelect.timestamper.user.VerificationCodeHash;

/**
 * Created by Michael on 2016/7/3.
 */
class VerificationCodeHashImpl implements VerificationCodeHash {

    private String code;

    private String salt;
    private String hash;

    private Date expiration;

    VerificationCodeHashImpl(MobileVerificationEntity entity) {
        code = entity.getCode();
        expiration = entity.getExpiration();
        hash();
    }

    VerificationCodeHashImpl(EmailVerificationEntity entity) {
        code = entity.getCode();
        expiration = entity.getExpiration();
        hash();
    }

    @Override
    public String getCodeHash() {
        return hash;
    }

    @Override
    public String getCodeHashSalt() {
        return salt;
    }

    @Override
    public Date getExpiration() {
        return expiration;
    }

    private void hash() {
        byte[] saltBytes = RandomUtils.nextBytes(16);
        salt = Hex.encodeHexString(saltBytes);

        PBEKeySpec spec = new PBEKeySpec(code.toCharArray(), saltBytes, 1000, 256);

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hashBytes = skf.generateSecret(spec).getEncoded();
            hash = Hex.encodeHexString(hashBytes);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
