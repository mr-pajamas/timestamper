package org.nelect.timestamper.internal;

import java.util.Properties;

import org.nelect.timestamper.*;
import org.nelect.timestamper.internal.interceptors.*;
import org.nelect.timestamper.internal.persistence.Context;
import org.nelect.timestamper.partner.*;

/**
 * Created by Michael on 2016/5/31.
 */
public class SessionImpl implements Session, CommandContext, CommandExecutor {

    private Principal principal;

    private Context persistenceContext;

    private Properties config;

    private CommandExecutor executor;
    private CommandInterceptor first;
    private CommandInterceptor last;

    private CreditworthinessQueryService creditworthinessQueryService;

    public SessionImpl(Context persistenceContext) {
        this.persistenceContext = persistenceContext;

        executor = new CommandExecutor() {

            @Override
            public <R> R execute(Command<R> command) throws TimestamperException {
                return command.doExecute(SessionImpl.this);
            }
        };

        addInterceptor(new LoggingInterceptor());
        addInterceptor(new ValidationInterceptor());
        addInterceptor(new TransactionInterceptor(persistenceContext));
    }

    private void addInterceptor(CommandInterceptor interceptor) {
        if (last != null)
            last.setNext(interceptor);
        else
            first = interceptor;

        interceptor.setNext(executor);
        last = interceptor;
    }

    @Override
    public Properties getConfig() {
        if (config == null) config = new Properties();
        return config;
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
    public CommandExecutor getExecutor() {
        return this;
    }

    @Override
    public Context getPersistenceContext() {
        return persistenceContext;
    }

    @Override
    public <R> R execute(Command<R> command) throws TimestamperException {
        if (first != null)
            return first.execute(command);
        else
            return executor.execute(command);
    }


    @Override
    public CreditworthinessQueryService getCreditworthinessQueryService() {
        if (creditworthinessQueryService == null)
            creditworthinessQueryService = new CreditworthinessQueryServiceImpl(this);
        return creditworthinessQueryService;
    }

    @Override
    public EContractQueryService getEContractQueryService() {
        return null;
    }

    @Override
    public EInvoiceQueryService getEInvoiceQueryService() {
        return null;
    }
}
