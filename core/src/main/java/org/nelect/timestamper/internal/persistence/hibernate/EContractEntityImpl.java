package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.Date;

import org.nelect.timestamper.internal.persistence.EContractEntity;

/**
 * Created by Michael on 2016/5/30.
 */
class EContractEntityImpl implements EContractEntity {

    private String id;

    private String checkId;
    private String principal;
    private String certNumber;
    private Date registrationTime;
    private String attachmentName;
    private String attachmentContentType;
    private String attachmentPath;
    private String attachmentChecksum;
    private String digest;
    private String transactionId;
    private Boolean confident;

    @Override
    public String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    @Override
    public String getCheckId() {
        return checkId;
    }

    void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    @Override
    public String getPrincipal() {
        return principal;
    }

    void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Override
    public String getCertNumber() {
        return certNumber;
    }

    void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }

    @Override
    public Date getRegistrationTime() {
        return registrationTime;
    }

    void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    @Override
    public String getAttachmentName() {
        return attachmentName;
    }

    void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    @Override
    public String getAttachmentContentType() {
        return attachmentContentType;
    }

    void setAttachmentContentType(String attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
    }

    @Override
    public String getAttachmentPath() {
        return attachmentPath;
    }

    void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Override
    public String getAttachmentChecksum() {
        return attachmentChecksum;
    }

    void setAttachmentChecksum(String attachmentChecksum) {
        this.attachmentChecksum = attachmentChecksum;
    }

    @Override
    public String getDigest() {
        return digest;
    }

    void setDigest(String digest) {
        this.digest = digest;
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }

    void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public Boolean getConfident() {
        return confident;
    }

    void setConfident(Boolean confident) {
        this.confident = confident;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EContractEntityImpl that = (EContractEntityImpl) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!checkId.equals(that.checkId)) return false;
        if (!principal.equals(that.principal)) return false;
        if (!certNumber.equals(that.certNumber)) return false;
        if (registrationTime != null ? !registrationTime.equals(that.registrationTime) : that.registrationTime != null)
            return false;
        if (!attachmentName.equals(that.attachmentName)) return false;
        if (!attachmentContentType.equals(that.attachmentContentType))
            return false;
        if (!attachmentPath.equals(that.attachmentPath)) return false;
        if (!attachmentChecksum.equals(that.attachmentChecksum)) return false;
        if (!digest.equals(that.digest)) return false;
        if (!transactionId.equals(that.transactionId)) return false;
        return confident != null ? confident.equals(that.confident) : that.confident == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + checkId.hashCode();
        result = 31 * result + principal.hashCode();
        result = 31 * result + certNumber.hashCode();
        result = 31 * result + (registrationTime != null ? registrationTime.hashCode() : 0);
        result = 31 * result + attachmentName.hashCode();
        result = 31 * result + attachmentContentType.hashCode();
        result = 31 * result + attachmentPath.hashCode();
        result = 31 * result + attachmentChecksum.hashCode();
        result = 31 * result + digest.hashCode();
        result = 31 * result + transactionId.hashCode();
        result = 31 * result + (confident != null ? confident.hashCode() : 0);
        return result;
    }
}
