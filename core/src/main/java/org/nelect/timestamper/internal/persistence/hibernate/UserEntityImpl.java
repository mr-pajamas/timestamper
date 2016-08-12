package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.Date;

import org.nelect.timestamper.internal.persistence.UserEntity;

/**
 * Created by Michael on 2016/7/5.
 */
class UserEntityImpl implements UserEntity {

    private String id;

    private String email;
    private String mobile;
    private String password;
    private Date creationTime;
    private IdentityType identityType;
    private Date verificationTime;
    private String verificationFailReasons;
    private Integer certificateCount;
    private Integer balance;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public IdentityType getIdentityType() {
        return identityType;
    }

    public void setIdentityType(IdentityType identityType) {
        this.identityType = identityType;
    }

    @Override
    public Date getVerificationTime() {
        return verificationTime;
    }

    public void setVerificationTime(Date verificationTime) {
        this.verificationTime = verificationTime;
    }

    @Override
    public String getVerificationFailReasons() {
        return verificationFailReasons;
    }

    public void setVerificationFailReasons(String verificationFailReasons) {
        this.verificationFailReasons = verificationFailReasons;
    }

    @Override
    public Integer getCertificateCount() {
        return certificateCount;
    }

    public void setCertificateCount(Integer certificateCount) {
        this.certificateCount = certificateCount;
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntityImpl that = (UserEntityImpl) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null)
            return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null)
            return false;
        if (!password.equals(that.password)) return false;
        if (!creationTime.equals(that.creationTime)) return false;
        if (identityType != that.identityType) return false;
        if (verificationTime != null ? !verificationTime.equals(that.verificationTime) : that.verificationTime != null)
            return false;
        if (verificationFailReasons != null ? !verificationFailReasons.equals(that.verificationFailReasons) : that.verificationFailReasons != null)
            return false;
        if (certificateCount != null ? !certificateCount.equals(that.certificateCount) : that.certificateCount != null)
            return false;
        return balance != null ? balance.equals(that.balance) : that.balance == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + password.hashCode();
        result = 31 * result + creationTime.hashCode();
        result = 31 * result + identityType.hashCode();
        result = 31 * result + (verificationTime != null ? verificationTime.hashCode() : 0);
        result = 31 * result + (verificationFailReasons != null ? verificationFailReasons.hashCode() : 0);
        result = 31 * result + (certificateCount != null ? certificateCount.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }
}
