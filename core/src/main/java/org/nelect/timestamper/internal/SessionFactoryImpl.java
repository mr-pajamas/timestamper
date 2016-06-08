package org.nelect.timestamper.internal;

import java.util.Properties;

import org.nelect.timestamper.*;
import org.nelect.timestamper.internal.agent.TimestampAgent;
import org.nelect.timestamper.internal.persistence.ContextFactory;

/**
 * Created by Michael on 2016/5/30.
 */
public class SessionFactoryImpl implements SessionFactory {

    private final ContextFactory contextFactory;

    private final TimestampAgent timestampAgent;

    private Properties commandContextConfig;

    public SessionFactoryImpl(ContextFactory contextFactory, TimestampAgent timestampAgent) {
        this.contextFactory = contextFactory;
        this.timestampAgent = timestampAgent;
    }

    public void setCommandContextConfig(Properties commandContextConfig) {
        this.commandContextConfig = commandContextConfig;
    }

    @Override
    public Session newSession(Principal principal) {
        SessionImpl session = new SessionImpl(contextFactory.newContext(), timestampAgent);
        session.setPrincipal(principal);
        if (commandContextConfig != null) session.setConfig(commandContextConfig);

        return session;
    }

    @Override
    public Session newSession() {
        SessionImpl session = new SessionImpl(contextFactory.newContext(), timestampAgent);
        if (commandContextConfig != null) session.setConfig(commandContextConfig);

        return session;
    }
}
