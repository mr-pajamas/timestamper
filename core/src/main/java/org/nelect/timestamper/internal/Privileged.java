package org.nelect.timestamper.internal;

import java.lang.annotation.*;

import org.nelect.timestamper.Principal;

/**
 * Created by Michael on 2016/7/4.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Privileged {

    Class<? extends Principal> value() default Principal.class;
}
