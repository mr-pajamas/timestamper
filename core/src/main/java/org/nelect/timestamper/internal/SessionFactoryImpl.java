package org.nelect.timestamper.internal;

import java.util.Properties;

import org.nelect.timestamper.*;
import org.nelect.timestamper.internal.persistence.ContextFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Michael on 2016/5/30.
 */
public class SessionFactoryImpl implements SessionFactory, ApplicationContextAware {

    private final ContextFactory persistenceContextFactory;

    //private final TimestampAgent timestampAgent;

    private ApplicationContext applicationContext;

    private Properties commandContextConfig;

    public SessionFactoryImpl(ContextFactory persistenceContextFactory) {
        this.persistenceContextFactory = persistenceContextFactory;
        //this.timestampAgent = timestampAgent;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setCommandContextConfig(Properties commandContextConfig) {
        this.commandContextConfig = commandContextConfig;
    }

    @Override
    public Session newSession(Principal principal) {
        SessionImpl session = new SessionImpl(persistenceContextFactory, applicationContext);
        if (commandContextConfig != null) session.setConfig(commandContextConfig);
        session.setPrincipal(principal);

        return session;
    }

    @Override
    public Session newSession() {
        SessionImpl session = new SessionImpl(persistenceContextFactory, applicationContext);
        if (commandContextConfig != null) session.setConfig(commandContextConfig);

        return session;
    }

    @Override
    public AdminSession newAdminSession() {
        AdminSessionImpl adminSession = new AdminSessionImpl(persistenceContextFactory, applicationContext);
        if (commandContextConfig != null) adminSession.setConfig(commandContextConfig);
        return adminSession;
    }
}
