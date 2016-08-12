package org.nelect.timestamper.internal;

import java.util.Properties;

import org.nelect.timestamper.*;
import org.nelect.timestamper.internal.interceptors.*;
import org.nelect.timestamper.internal.persistence.Context;
import org.nelect.timestamper.internal.persistence.ContextFactory;
import org.nelect.timestamper.partner.*;
import org.nelect.timestamper.user.AccountService;
import org.nelect.timestamper.user.IdentityService;
import org.springframework.context.ApplicationContext;

/**
 * Created by Michael on 2016/5/31.
 */
public class SessionImpl implements Session, CommandExecutor, CommandContextFactory {

    private Principal principal;

    private ContextFactory persistenceContextFactory;

    private ApplicationContext applicationContext;

    private Properties config = new Properties();

    private CommandExecutor executor;
    private CommandInterceptor first;
    private CommandInterceptor last;

    private AccountService accountService;
    private AttachmentService attachmentService;
    private IdentityService identityService;
    private CertificateService certificateService;
    private CreditworthinessService creditworthinessService;
    private EContractService eContractService;
    private EInvoiceService eInvoiceService;

    public SessionImpl(ContextFactory persistenceContextFactory, ApplicationContext applicationContext) {
        this.persistenceContextFactory = persistenceContextFactory;
        //this.timestampAgent = timestampAgent;
        this.applicationContext = applicationContext;

        executor = new CommandExecutor() {

            @Override
            public <R> R execute(Command<R> command, CommandContext context) throws TimestamperException {
                return command.doExecute(context);
            }
        };

        addInterceptor(new LoggingInterceptor());
        addInterceptor(new AccessControlInterceptor());
        addInterceptor(new ValidationInterceptor());
        addInterceptor(new TransactionInterceptor());
    }

    private void addInterceptor(CommandInterceptor interceptor) {
        if (last != null)
            last.setNext(interceptor);
        else
            first = interceptor;

        interceptor.setNext(executor);
        last = interceptor;
    }

    public void setConfig(Properties config) {
        this.config = config;
    }

    @Override
    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    @Override
    public <R> R execute(Command<R> command, CommandContext context) throws TimestamperException {
        if (first != null)
            return first.execute(command, context);
        else
            return executor.execute(command, context);
    }

    @Override
    public CommandContext newCommandContext() {
        return new CommandContextImpl(persistenceContextFactory.newContext());
    }

    @Override
    public AccountService getAccountService() {
        if (accountService == null)
            accountService = new AccountServiceImpl(this, this);
        return accountService;
    }

    @Override
    public AttachmentService getAttachmentService() {
        if (attachmentService == null)
            attachmentService = new AttachmentServiceImpl(this, this);
        return attachmentService;
    }

    @Override
    public IdentityService getIdentityService() {
        if (identityService == null)
            identityService = new IdentityServiceImpl(this, this);
        return identityService;
    }

    @Override
    public CertificateService getCertificateService() {
        if (certificateService == null)
            certificateService = new CertificateServiceImpl(this, this);
        return certificateService;
    }

    @Override
    public CreditworthinessService getCreditworthinessService() {
        if (creditworthinessService == null)
            creditworthinessService = new CreditworthinessServiceImpl(this, this);
        return creditworthinessService;
    }

    @Override
    public EContractService getEContractService() {
        if (eContractService == null)
            eContractService = new EContractServiceImpl(this, this);
        return eContractService;
    }

    @Override
    public EInvoiceService getEInvoiceService() {
        if (eInvoiceService == null)
            eInvoiceService = new EInvoiceServiceImpl(this, this);
        return eInvoiceService;
    }

    private class CommandContextImpl implements CommandContext {

        private Context persistenceContext;

        CommandContextImpl(Context persistenceContext) {
            this.persistenceContext = persistenceContext;
        }

        @Override
        public CommandExecutor getExecutor() {
            return SessionImpl.this;
        }

        @Override
        public CommandContextFactory getContextFactory() {
            return SessionImpl.this;
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
            return principal;
        }

        @Override
        public boolean isAdmin() {
            return false;
        }
    }
}
