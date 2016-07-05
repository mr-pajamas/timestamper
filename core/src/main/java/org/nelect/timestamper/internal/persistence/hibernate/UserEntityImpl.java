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
    private Date verificationTime;

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
    public Date getVerificationTime() {
        return verificationTime;
    }

    public void setVerificationTime(Date verificationTime) {
        this.verificationTime = verificationTime;
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
        return verificationTime != null ? verificationTime.equals(that.verificationTime) : that.verificationTime == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + password.hashCode();
        result = 31 * result + creationTime.hashCode();
        result = 31 * result + (verificationTime != null ? verificationTime.hashCode() : 0);
        return result;
    }
}
