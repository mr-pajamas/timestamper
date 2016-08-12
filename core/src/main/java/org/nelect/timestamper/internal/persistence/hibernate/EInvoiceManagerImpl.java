package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.nelect.timestamper.internal.persistence.*;

/**
 * Created by Michael on 2016/6/10.
 */
class EInvoiceManagerImpl implements EInvoiceManager {

    private ContextImpl context;

    EInvoiceManagerImpl(ContextImpl context) {
        this.context = context;
    }

    @Override
    public EInvoiceUpdater newUpdater() {
        return new EInvoiceUpdaterImpl();
    }

    @Override
    public EInvoiceUpdater newUpdater(String id) {
        return new EInvoiceUpdaterImpl(id);
    }

    @Override
    public EInvoiceUpdater newUpdater(EInvoiceEntity entity) {
        return new EInvoiceUpdaterImpl((EInvoiceEntityImpl) entity);
    }

    @Override
    public EInvoiceEntity get(String id) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return session.get(EInvoiceEntity.class, id);
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public EInvoiceEntity getByTransactionId(String transactionId) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (EInvoiceEntity) session.createCriteria(EInvoiceEntity.class)
                .add(Restrictions.eq("transactionId", transactionId))
                .uniqueResult();
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public EInvoiceEntity getByDigest(String digest) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (EInvoiceEntity) session.createCriteria(EInvoiceEntity.class)
                .add(Restrictions.eq("digest", digest))
                .uniqueResult();
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public EInvoiceEntity getByCheckId(String checkId) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (EInvoiceEntity) session.createCriteria(EInvoiceEntity.class)
                .add(Restrictions.eq("checkId", checkId))
                .uniqueResult();
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public EInvoiceEntity getByCertNumber(String certNumber) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (EInvoiceEntity) session.createCriteria(EInvoiceEntity.class)
                .add(Restrictions.eq("certNumber", certNumber))
                .uniqueResult();
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public EInvoiceEntity getByChecksum(String checksum) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (EInvoiceEntity) session.createCriteria(EInvoiceEntity.class)
                .add(Restrictions.eq("checksum", checksum))
                .uniqueResult();
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public EInvoiceQuery newQuery() {
        return new EInvoiceQueryImpl();
    }

    private class EInvoiceUpdaterImpl implements EInvoiceUpdater {

        private EInvoiceEntityImpl entity;

        private EInvoiceUpdaterImpl() {
            this.entity = new EInvoiceEntityImpl();
        }

        private EInvoiceUpdaterImpl(String id) {
            this.entity = new EInvoiceEntityImpl();
            entity.setId(id);
        }

        private EInvoiceUpdaterImpl(EInvoiceEntityImpl entity) {
            this.entity = entity;
        }

        @Override
        public EInvoiceUpdater setCheckId(String checkId) {
            entity.setCheckId(checkId);
            return this;
        }

        @Override
        public EInvoiceUpdater setPrincipal(String principal) {
            entity.setPrincipal(principal);
            return this;
        }

        @Override
        public EInvoiceUpdater setCertNumber(String certNumber) {
            entity.setCertNumber(certNumber);
            return this;
        }

        @Override
        public EInvoiceUpdater setChecksum(String checksum) {
            entity.setChecksum(checksum);
            return this;
        }

        @Override
        public EInvoiceUpdater setDigest(String digest) {
            entity.setDigest(digest);
            return this;
        }

        @Override
        public EInvoiceUpdater setTransactionId(String transactionId) {
            entity.setTransactionId(transactionId);
            return this;
        }

        @Override
        public EInvoiceUpdater setRegistrationTime(Date registrationTime) {
            entity.setRegistrationTime(registrationTime);
            return this;
        }

        @Override
        public EInvoiceUpdater setConfident(Boolean confident) {
            entity.setConfident(confident);
            return this;
        }

        @Override
        public EInvoiceEntity save() {
            boolean failed = false;
            Session session = context.getSession();
            try {
                session.saveOrUpdate(entity);
                return entity;
            } catch (RuntimeException re) {
                failed = true;
                throw re;
            } finally {
                context.releaseSession(failed);
            }
        }
    }

    private class EInvoiceQueryImpl implements EInvoiceQuery {

        private DetachedCriteria criteria = DetachedCriteria.forClass(EInvoiceEntityImpl.class);

        @Override
        public EInvoiceQuery registrationTimeExists() {
            criteria.add(Restrictions.isNotNull("registrationTime"));
            return this;
        }

        @Override
        public EInvoiceQuery confident() {
            criteria.add(Restrictions.eq("confident", true));
            return this;
        }

        @Override
        public EInvoiceQuery orderByRegistrationTime(boolean desc) {
            criteria.addOrder(desc ? Order.desc("registrationTime") : Order.asc("registrationTime"));
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public List<EInvoiceEntity> list() {
            boolean failed = false;
            Session session = context.getSession();
            try {
                return criteria.getExecutableCriteria(session).list();
            } catch (RuntimeException re) {
                failed = true;
                throw re;
            } finally {
                context.releaseSession(failed);
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public List<EInvoiceEntity> list(int offset, int limit) {
            boolean failed = false;
            Session session = context.getSession();
            try {
                return criteria.getExecutableCriteria(session)
                    .setFirstResult(offset).setMaxResults(limit).list();
            } catch (RuntimeException re) {
                failed = true;
                throw re;
            } finally {
                context.releaseSession(failed);
            }
        }

        @Override
        public int count() {
            boolean failed = false;
            Session session = context.getSession();
            try {
                return (Integer) criteria.getExecutableCriteria(session)
                    .setProjection(Projections.rowCount()).uniqueResult();
            } catch (RuntimeException re) {
                failed = true;
                throw re;
            } finally {
                context.releaseSession(failed);
            }
        }
    }
}
