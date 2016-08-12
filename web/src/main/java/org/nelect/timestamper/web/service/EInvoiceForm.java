package org.nelect.timestamper.web.service;

import org.hibernate.validator.constraints.NotBlank;
import org.nelect.timestamper.partner.EInvoiceInput;

/**
 * Created by Michael on 2016/6/10.
 */
public class EInvoiceForm implements EInvoiceInput {

    private String checkId;
    private String certNumber;
    private String checksum;
    private String principal;

    @NotBlank(message = "000801")
    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    @NotBlank(message = "000803")
    public String getCertNumber() {
        return certNumber;
    }

    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }

    @NotBlank(message = "000804")
    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    @NotBlank(message = "000802")
    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPrincipalName() {
        return getPrincipal();
    }
}
