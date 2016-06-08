package org.nelect.timestamper.server;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Michael on 2016/6/5.
 */
public class ProofStore {

    private SessionFactory sessionFactory;

    private static final ThreadLocal<Session> threadSession = new ThreadLocal<>();

    public ProofStore() {
        sessionFactory = new MetadataSources(new StandardServiceRegistryBuilder().configure().build())
            .addResource("org/nelect/timestamper/server/proof.hbm.xml")
            .buildMetadata()
            .buildSessionFactory();
    }

    @SuppressWarnings("unchecked")
    public List<Proof> getPendingProofs() {
        Session session = threadSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            try {
                return session.createCriteria(Proof.class).add(Restrictions.isNull("blockHash")).list();
            } finally {
                closeQuietly(session);
            }
        } else {
            return session.createCriteria(Proof.class).add(Restrictions.isNull("blockHash")).list();
        }
    }

    public Proof get(String transactionId) {
        Session session = threadSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            try {
                return session.get(Proof.class, transactionId);
            } finally {
                closeQuietly(session);
            }
        } else {
            return session.get(Proof.class, transactionId);
        }
    }

    public void save(Proof proof) {
        Session session = threadSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadSession.set(session);
        }

        session.saveOrUpdate(proof);
    }

    public void flush() {
        Session session = threadSession.get();
        //if (session == null) throw new IllegalStateException("没有任何写入操作，无法提交");
        if (session != null) {
            session.flush();
            closeQuietly(session);
            threadSession.remove();
        }
    }

    public void saveAndFlush(Proof proof) {

        Session session = threadSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            try {
                session.saveOrUpdate(proof);
                session.flush();
            } finally {
                closeQuietly(session);
            }
        } else {
            session.saveOrUpdate(proof);
            try {
                session.flush();
            } catch (RuntimeException re) {
                session.evict(proof);
                throw re;
            }
            closeQuietly(session);
            threadSession.remove();
        }
    }

    public void close() {
        sessionFactory.close();
    }

    private void closeQuietly(Session session) {
        try {
            session.close();
        } catch (Exception e) {
            // do nothing
        }
    }
}
