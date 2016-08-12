package org.nelect.timestamper.web.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.nelect.timestamper.partner.EInvoice;

/**
 * Created by Michael on 2016/6/10.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EInvoiceModel {

    private EInvoice eInvoice;

    public EInvoiceModel(EInvoice eInvoice) {
        this.eInvoice = eInvoice;
    }

    public String getCheckId() {
        return eInvoice.getCheckId();
    }

    public String getPrincipal() {
        return eInvoice.getPrincipalName();
    }

    public String getCertNumber() {
        return eInvoice.getCertNumber();
    }

    public String getChecksum() {
        return eInvoice.getChecksum();
    }

    public String getDigest() {
        return eInvoice.getDigest();
    }

    public String getTransactionId() {
        return eInvoice.getTransactionId();
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    public Date getRegistrationDate() {
        return eInvoice.getRegistrationTime();
    }
}
