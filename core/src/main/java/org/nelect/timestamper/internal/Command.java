package org.nelect.timestamper.internal;

import org.nelect.timestamper.TimestamperException;

/**
 * Created by Michael on 2016/5/30.
 */
public interface Command<R> {

    /**
     * Abstract method which encapsulates a domain procedure
     *
     * @param context the execution context of this command
     * @return the domain object which this command returns
     * @throws TimestamperException when a domain-specific error occurs
     */
    R doExecute(CommandContext context) throws TimestamperException;
}
