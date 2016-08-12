package org.nelect.timestamper;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * <p>TODO</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public class UnauthorizedActionException extends TimestamperException {

    private static final Map<Class<? extends Principal>, String> ROLE_NAME =
        ImmutableMap.<Class<? extends Principal>, String> builder()
            .put(Principal.class, "注册用户")
            .put(VerifiedPrincipal.class, "认证用户")
            .put(Individual.class, "认证个人用户")
            .put(Organization.class, "认证企业用户")
            .build();

    private Class<? extends Principal>[] requiredRoles;

    public UnauthorizedActionException(Class<? extends Principal>[] requiredRoles) {
        super("权限不足，无法执行该操作");
        this.requiredRoles = requiredRoles;
    }

    public Class<? extends Principal>[] getRequiredRoles() {
        return requiredRoles;
    }
}
