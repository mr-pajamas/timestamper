package org.nelect.timestamper.internal;

import java.util.Properties;

import org.nelect.timestamper.Principal;
import org.nelect.timestamper.internal.agent.TimestampAgent;
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

    //UserStore getUserStore();

    /**
     * Retrieve the {@link Context} of persistence layer in this context
     */
    Context getPersistenceContext();

    TimestampAgent getTimestampAgent();

    Properties getConfig();

    Principal getPrincipal();
}
