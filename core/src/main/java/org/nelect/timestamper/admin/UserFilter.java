package org.nelect.timestamper.admin;

/**
 * Created by Michael on 2016/7/18.
 */
public class UserFilter {

    private IdentityType identityType;
    private Boolean unverified;

    public IdentityType getIdentityType() {
        return identityType;
    }

    public void setIdentityType(IdentityType identityType) {
        this.identityType = identityType;
    }

    public Boolean getUnverified() {
        return unverified;
    }

    public void setUnverified(Boolean unverified) {
        this.unverified = unverified;
    }

    public static enum IdentityType {
        INDIVIDUAL,
        ORGANIZATION
    }
}
