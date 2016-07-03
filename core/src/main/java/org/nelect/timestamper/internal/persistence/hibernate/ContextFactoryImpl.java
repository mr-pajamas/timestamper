package org.nelect.timestamper.internal.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.nelect.timestamper.internal.persistence.Context;
import org.nelect.timestamper.internal.persistence.ContextFactory;

/**
 * Created by Michael on 2016/5/30.
 */
public class ContextFactoryImpl implements ContextFactory {

    private final SessionFactory sessionFactory;

    public ContextFactoryImpl() {
        sessionFactory = new MetadataSources(new StandardServiceRegistryBuilder().configure().build())
            .addResource("org/nelect/timestamper/internal/persistence/hibernate/credit-info.hbm.xml")
            .addResource("org/nelect/timestamper/internal/persistence/hibernate/e-contract.hbm.xml")
            .addResource("org/nelect/timestamper/internal/persistence/hibernate/e-invoice.hbm.xml")
            .addResource("org/nelect/timestamper/internal/persistence/hibernate/mobile-verification.hbm.xml")
            .addResource("org/nelect/timestamper/internal/persistence/hibernate/email-verification.hbm.xml")
            .buildMetadata()
            .buildSessionFactory();
    }

    @Override
    public Context newContext() {
        return new ContextImpl(this);
    }

    public void close() {
        sessionFactory.close();
    }

    Session newSession() {
        return sessionFactory.openSession();
    }

    static void closeQuietly(Session session) {
        try {
            session.close();
        } catch (Exception e) {
            // do nothing
        }
    }
}
