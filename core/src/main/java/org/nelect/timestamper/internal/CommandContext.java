package org.nelect.timestamper.internal;

import java.util.Properties;

import org.nelect.timestamper.Principal;
import org.nelect.timestamper.internal.persistence.Context;

/**
 * <p>Execution context of domain commands</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public interface CommandContext {

    /**
     * Retrieve the {@link CommandExecutor} in this context
     */
    CommandExecutor getExecutor();

    CommandContextFactory getContextFactory();

    //UserStore getUserStore();

    /**
     * Retrieve the {@link Context} of persistence layer in this context
     */
    Context getPersistenceContext();

    //TimestampAgent getTimestampAgent();

    <C> C getComponent(Class<C> componentClass);

    Properties getConfig();

    Principal getPrincipal();
}
