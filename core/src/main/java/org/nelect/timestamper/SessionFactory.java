package org.nelect.timestamper;

/**
 * <p>TODO</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public interface SessionFactory {

    Session newSession(Principal principal);

    Session newSession();
}
