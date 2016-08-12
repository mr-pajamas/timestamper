package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.Date;

import org.nelect.timestamper.internal.persistence.CertificateEntity;

/**
 * Created by Michael on 2016/7/3.
 */
class CertificateEntityImpl implements CertificateEntity {

    private String id;

    private String title;
    private String principalId;
/*    private String attachmentName;
    private String attachmentContentType;
    private String attachmentPath;*/
    private String attachmentId;
    private String digest;
    private String transactionId;
    private Date registrationTime;
    private Boolean confident;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

/*
    @Override
    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    @Override
    public String getAttachmentContentType() {
        return attachmentContentType;
    }

    public void setAttachmentContentType(String attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
    }

    @Override
    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
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

    public void setDigest(String digest) {
        this.digest = digest;
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    @Override
    public Boolean getConfident() {
        return confident;
    }

    public void setConfident(Boolean confident) {
        this.confident = confident;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CertificateEntityImpl that = (CertificateEntityImpl) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!title.equals(that.title)) return false;
        if (!principalId.equals(that.principalId)) return false;
        if (!attachmentId.equals(that.attachmentId)) return false;
        if (!digest.equals(that.digest)) return false;
        if (!transactionId.equals(that.transactionId)) return false;
        if (registrationTime != null ? !registrationTime.equals(that.registrationTime) : that.registrationTime != null)
            return false;
        return confident != null ? confident.equals(that.confident) : that.confident == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + principalId.hashCode();
        result = 31 * result + attachmentId.hashCode();
        result = 31 * result + digest.hashCode();
        result = 31 * result + transactionId.hashCode();
        result = 31 * result + (registrationTime != null ? registrationTime.hashCode() : 0);
        result = 31 * result + (confident != null ? confident.hashCode() : 0);
        return result;
    }
}
