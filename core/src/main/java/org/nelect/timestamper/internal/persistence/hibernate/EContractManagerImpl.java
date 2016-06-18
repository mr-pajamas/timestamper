package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.nelect.timestamper.internal.persistence.*;

/**
 * Created by Michael on 2016/6/10.
 */
class EContractManagerImpl implements EContractManager {

    private ContextImpl context;

    EContractManagerImpl(ContextImpl context) {
        this.context = context;
    }

    @Override
    public EContractUpdater newUpdater() {
        return new EContractUpdaterImpl();
    }

    @Override
    public EContractUpdater newUpdater(String id) {
        return new EContractUpdaterImpl(id);
    }

    @Override
    public EContractUpdater newUpdater(EContractEntity entity) {
        return new EContractUpdaterImpl((EContractEntityImpl) entity);
    }

    @Override
    public EContractEntity get(String id) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return session.get(EContractEntity.class, id);
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public EContractEntity getByTransactionId(String transactionId) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (EContractEntity) session.createCriteria(EContractEntity.class)
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
    public EContractEntity getByDigest(String digest) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (EContractEntity) session.createCriteria(EContractEntity.class)
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
    public EContractEntity getByCheckId(String checkId) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (EContractEntity) session.createCriteria(EContractEntity.class)
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
    public EContractEntity getByChecksum(String checksum) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (EContractEntity) session.createCriteria(EContractEntity.class)
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
    public EContractQuery newQuery() {
        return new EContractQueryImpl();
    }

    private class EContractUpdaterImpl implements EContractUpdater {

        private EContractEntityImpl entity;

        private EContractUpdaterImpl() {
            this.entity = new EContractEntityImpl();
        }

        private EContractUpdaterImpl(String id) {
            this.entity = new EContractEntityImpl();
            entity.setId(id);
        }

        private EContractUpdaterImpl(EContractEntityImpl entity) {
            this.entity = entity;
        }

        @Override
        public EContractUpdater setCheckId(String checkId) {
            entity.setCheckId(checkId);
            return this;
        }

        @Override
        public EContractUpdater setPrincipal(String principal) {
            entity.setPrincipal(principal);
            return this;
        }

        @Override
        public EContractUpdater setCertNumber(String certNumber) {
            entity.setCertNumber(certNumber);
            return this;
        }

        @Override
        public EContractUpdater setChecksum(String checksum) {
            entity.setChecksum(checksum);
            return this;
        }

        @Override
        public EContractUpdater setDigest(String digest) {
            entity.setDigest(digest);
            return this;
        }

        @Override
        public EContractUpdater setTransactionId(String transactionId) {
            entity.setTransactionId(transactionId);
            return this;
        }

        @Override
        public EContractUpdater setRegistrationTime(Date registrationTime) {
            entity.setRegistrationTime(registrationTime);
            return this;
        }

        @Override
        public EContractUpdater setConfident(Boolean confident) {
            entity.setConfident(confident);
            return this;
        }

        @Override
        public EContractEntity save() {
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

    private class EContractQueryImpl implements EContractQuery {

        private DetachedCriteria criteria = DetachedCriteria.forClass(EContractEntityImpl.class);

        @Override
        public EContractQuery registrationTimeExists() {
            criteria.add(Restrictions.isNotNull("registrationTime"));
            return this;
        }

        @Override
        public EContractQuery confident() {
            criteria.add(Restrictions.eq("confident", true));
            return this;
        }

        @Override
        public EContractQuery orderByRegistrationTime(boolean desc) {
            criteria.addOrder(desc ? Order.desc("registrationTime") : Order.asc("registrationTime"));
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public List<EContractEntity> list() {
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
        public List<EContractEntity> list(int offset, int limit) {
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
