package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.Date;

import org.nelect.timestamper.internal.persistence.CreditInfoEntity;
import org.nelect.timestamper.internal.persistence.CreditInfoEntityType;

/**
 * Created by Michael on 2016/5/30.
 */
class CreditInfoEntityImpl implements CreditInfoEntity {

    private String id;

    private CreditInfoEntityType type;
    private String checkId;
    private String name;
    private String principal;
    private Date registrationTime;
/*    private String attachmentName;
    private String attachmentContentType;
    private String attachmentPath;*/
    private String attachmentId;
    private String digest;
    private String details;
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
    public CreditInfoEntityType getType() {
        return type;
    }

    void setType(CreditInfoEntityType type) {
        this.type = type;
    }

    @Override
    public String getCheckId() {
        return checkId;
    }

    void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    @Override
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPrincipal() {
        return principal;
    }

    void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Override
    public Date getRegistrationTime() {
        return registrationTime;
    }

    void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

/*
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
*/

    @Override
    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    @Override
    public String getDigest() {
        return digest;
    }

    void setDigest(String digest) {
        this.digest = digest;
    }

    @Override
    public String getDetails() {
        return details;
    }

    void setDetails(String details) {
        this.details = details;
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

        CreditInfoEntityImpl that = (CreditInfoEntityImpl) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != that.type) return false;
        if (!checkId.equals(that.checkId)) return false;
        if (!name.equals(that.name)) return false;
        if (!principal.equals(that.principal)) return false;
        if (registrationTime != null ? !registrationTime.equals(that.registrationTime) : that.registrationTime != null)
            return false;
        if (!attachmentId.equals(that.attachmentId)) return false;
        if (!digest.equals(that.digest)) return false;
        if (!details.equals(that.details)) return false;
        if (!transactionId.equals(that.transactionId)) return false;
        return confident != null ? confident.equals(that.confident) : that.confident == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + type.hashCode();
        result = 31 * result + checkId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + principal.hashCode();
        result = 31 * result + (registrationTime != null ? registrationTime.hashCode() : 0);
        result = 31 * result + attachmentId.hashCode();
        result = 31 * result + digest.hashCode();
        result = 31 * result + details.hashCode();
        result = 31 * result + transactionId.hashCode();
        result = 31 * result + (confident != null ? confident.hashCode() : 0);
        return result;
    }
}
