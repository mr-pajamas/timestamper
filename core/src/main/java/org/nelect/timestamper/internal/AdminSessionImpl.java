package org.nelect.timestamper.internal;

import java.util.Properties;

import org.nelect.timestamper.AdminSession;
import org.nelect.timestamper.Principal;
import org.nelect.timestamper.admin.UserManagementService;
import org.nelect.timestamper.internal.persistence.Context;
import org.nelect.timestamper.internal.persistence.ContextFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created by Michael on 2016/7/18.
 */
public class AdminSessionImpl implements AdminSession, CommandContextFactory {

    private CommandExecutor executor;
    private ContextFactory persistenceContextFactory;
    private ApplicationContext applicationContext;

    private Properties config = new Properties();

    private UserManagementService userManagementService;

    public AdminSessionImpl(ContextFactory persistenceContextFactory, ApplicationContext applicationContext) {
        executor = new SessionImpl(persistenceContextFactory, applicationContext);
        this.persistenceContextFactory = persistenceContextFactory;
        this.applicationContext = applicationContext;
    }

    public void setConfig(Properties config) {
        this.config = config;
    }

    @Override
    public UserManagementService getUserManagementService() {
        if (userManagementService == null)
            userManagementService = new UserManagementServiceImpl(executor, this);
        return userManagementService;
    }

    @Override
    public CommandContext newCommandContext() {
        return new CommandContextImpl(persistenceContextFactory.newContext());
    }

    private class CommandContextImpl implements CommandContext {

        private Context persistenceContext;

        CommandContextImpl(Context persistenceContext) {
            this.persistenceContext = persistenceContext;
        }

        @Override
        public CommandExecutor getExecutor() {
            return executor;
        }

        @Override
        public CommandContextFactory getContextFactory() {
            return AdminSessionImpl.this;
        }

        @Override
        public Context getPersistenceContext() {
            return persistenceContext;
        }

        @Override
        public <C> C getComponent(Class<C> componentClass) {
            return applicationContext.getBean(componentClass);
        }

        @Override
        public Properties getConfig() {
            return config;
        }

        @Override
        public Principal getPrincipal() {
            return null;
        }

        @Override
        public boolean isAdmin() {
            return true;
        }
    }
}
