package org.nelect.timestamper.web.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.nelect.timestamper.user.VerificationCodeHash;

/**
 * Created by Michael on 2016/7/2.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VerificationCodeHashModel {

    private VerificationCodeHash verificationCodeHash;

    public VerificationCodeHashModel(VerificationCodeHash verificationCodeHash) {
        this.verificationCodeHash = verificationCodeHash;
    }

    public String getCodeHash() {
        return verificationCodeHash.getCodeHash();
    }

    public String getCodeHashSalt() {
        return verificationCodeHash.getCodeHashSalt();
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    public Date getExpiration() {
        return verificationCodeHash.getExpiration();
    }
}
