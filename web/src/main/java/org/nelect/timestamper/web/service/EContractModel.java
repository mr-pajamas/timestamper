package org.nelect.timestamper.web.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.nelect.timestamper.partner.EContract;

/**
 * Created by Michael on 2016/6/10.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EContractModel {

    private EContract eContract;

    public EContractModel(EContract eContract) {
        this.eContract = eContract;
    }

    public String getCheckId() {
        return eContract.getCheckId();
    }

    public String getPrincipal() {
        return eContract.getPrincipalName();
    }

    public String getCertNumber() {
        return eContract.getCertNumber();
    }

    public String getChecksum() {
        return eContract.getChecksum();
    }

    public String getDigest() {
        return eContract.getDigest();
    }

    public String getTransactionId() {
        return eContract.getTransactionId();
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    public Date getRegistrationDate() {
        return eContract.getRegistrationTime();
    }
}
