package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.Date;

import org.nelect.timestamper.internal.persistence.MobileVerificationEntity;

/**
 * Created by Michael on 2016/7/1.
 */
class MobileVerificationEntityImpl implements MobileVerificationEntity {

    private String id;

    private String mobile;
    private String code;
    private Date expiration;
    private Boolean used;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Override
    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MobileVerificationEntityImpl that = (MobileVerificationEntityImpl) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!mobile.equals(that.mobile)) return false;
        if (!code.equals(that.code)) return false;
        if (!expiration.equals(that.expiration)) return false;
        return used != null ? used.equals(that.used) : that.used == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + mobile.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + expiration.hashCode();
        result = 31 * result + (used != null ? used.hashCode() : 0);
        return result;
    }
}
