package org.nelect.timestamper.internal.persistence.hibernate;

import org.hibernate.Session;
import org.nelect.timestamper.internal.persistence.*;

import static org.nelect.timestamper.internal.persistence.hibernate.ContextFactoryImpl.closeQuietly;

/**
 * Created by Michael on 2016/5/30.
 */
class ContextImpl implements Context {

    private final ContextFactoryImpl contextFactory;
    private Session session;
    private boolean inTransaction = false;

    private final CreditInfoManager creditInfoManager;
    private final EContractManager eContractManager;

    ContextImpl(ContextFactoryImpl contextFactory) {
        this.contextFactory = contextFactory;
        creditInfoManager = new CreditInfoManagerImpl(this);
        eContractManager = new EContractManagerImpl(this);
    }

    @Override
    public void beginTransaction() {
        if (inTransaction) throw new IllegalStateException("事务已经开启");
        inTransaction = true;
    }

    @Override
    public void endTransaction() {
        if (!inTransaction) throw new IllegalStateException("上下文中没有事务");
        inTransaction = false;

        if (session != null) {
            try {
                session.getTransaction().commit();
            } catch (RuntimeException re) {
                try {
                    session.getTransaction().rollback();
                } finally {
                    throw re;
                }
            } finally {
                closeQuietly(session);
                session = null;
            }
        }
    }

    @Override
    public void abortTransaction() {
        if (!inTransaction) throw new IllegalStateException("上下文中没有事务");
        inTransaction = false;

        if (session != null) {
            try {
                session.getTransaction().rollback();
            } finally {
                closeQuietly(session);
                session = null;
            }
        }
    }

    @Override
    public CreditInfoManager getCreditInfoManager() {
        return creditInfoManager;
    }

    @Override
    public EContractManager getEContractManager() {
        return eContractManager;
    }

    Session getSession() {
        if (session == null) {
            session = contextFactory.newSession();

            try {
                if (inTransaction) session.beginTransaction();
            } catch (RuntimeException re) {
                try {
                    session.close();
                } finally {
                    session = null;
                    throw re;
                }
            }
        }

        return session;
    }

    void releaseSession(boolean failed) {
        try {
            if (!failed) session.flush();
        } finally {
            session.clear();
            if (!inTransaction) {
                closeQuietly(session);
                session = null;
            }
        }
    }
}
