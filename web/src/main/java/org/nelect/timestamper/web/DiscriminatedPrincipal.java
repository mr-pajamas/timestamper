package org.nelect.timestamper.web;

import org.nelect.timestamper.*;

/**
 * Created by Michael on 2016/7/5.
 */
public class DiscriminatedPrincipal {

    private Principal principal;

    public DiscriminatedPrincipal(Principal principal) {
        this.principal = principal;
    }

    public Principal getValue() {
        return principal;
    }

    public Type getType() {
        if (principal instanceof VerifiedIndividual) {
            return Type.INDIVIDUAL;
        } else if (principal instanceof VerifiedOrganization) {
            return Type.ORGANIZATION;
        } else {
            return Type.UNVERIFIED;
        }
    }

    public enum Type {
        UNVERIFIED,
        INDIVIDUAL,
        ORGANIZATION
    }
}
