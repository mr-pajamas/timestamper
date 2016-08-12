package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.criterion.MatchMode;
import org.nelect.timestamper.internal.persistence.*;

/**
 * Created by Michael on 2016/5/30.
 */
class CreditInfoManagerImpl implements CreditInfoManager {

    private ContextImpl context;

    CreditInfoManagerImpl(ContextImpl context) {
        this.context = context;
    }

    @Override
    public CreditInfoUpdater newUpdater() {
        return new CreditInfoUpdaterImpl();
    }

    @Override
    public CreditInfoUpdater newUpdater(String id) {
        return new CreditInfoUpdaterImpl(id);
    }

    @Override
    public CreditInfoUpdater newUpdater(CreditInfoEntity entity) {
        return new CreditInfoUpdaterImpl((CreditInfoEntityImpl) entity);
    }

    @Override
    public CreditInfoEntity get(String id) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return session.get(CreditInfoEntity.class, id);
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public CreditInfoEntity getByTransactionId(String transactionId) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (CreditInfoEntity) session.createCriteria(CreditInfoEntity.class)
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
    public CreditInfoQuery newQuery() {
        return new CreditInfoQueryImpl();
    }

    private class CreditInfoUpdaterImpl implements CreditInfoUpdater {

        private CreditInfoEntityImpl entity;

        private CreditInfoUpdaterImpl() {
            this.entity = new CreditInfoEntityImpl();
        }

        private CreditInfoUpdaterImpl(String id) {
            this.entity = new CreditInfoEntityImpl();
            entity.setId(id);
        }

        private CreditInfoUpdaterImpl(CreditInfoEntityImpl entity) {
            this.entity = entity;
        }

        @Override
        public CreditInfoUpdater setType(CreditInfoEntityType type) {
            entity.setType(type);
            return this;
        }

        @Override
        public CreditInfoUpdater setCheckId(String checkId) {
            entity.setCheckId(checkId);
            return this;
        }

        @Override
        public CreditInfoUpdater setName(String name) {
            entity.setName(name);
            return this;
        }

        @Override
        public CreditInfoUpdater setPrincipal(String principal) {
            entity.setPrincipal(principal);
            return this;
        }

        @Override
        public CreditInfoUpdater setRegistrationTime(Date registrationTime) {
            entity.setRegistrationTime(registrationTime);
            return this;
        }

/*
        @Override
        public CreditInfoUpdater setAttachmentName(String attachmentName) {
            entity.setAttachmentName(attachmentName);
            return this;
        }

        @Override
        public CreditInfoUpdater setAttachmentContentType(String attachmentContentType) {
            entity.setAttachmentContentType(attachmentContentType);
            return this;
        }

        @Override
        public CreditInfoUpdater setAttachmentPath(String attachmentPath) {
            entity.setAttachmentPath(attachmentPath);
            return this;
        }
*/

        @Override
        public CreditInfoUpdater setAttachmentId(String attachmentId) {
            entity.setAttachmentId(attachmentId);
            return this;
        }

        @Override
        public CreditInfoUpdater setDigest(String digest) {
            entity.setDigest(digest);
            return this;
        }

        @Override
        public CreditInfoUpdater setDetails(String details) {
            entity.setDetails(details);
            return this;
        }

        @Override
        public CreditInfoUpdater setTransactionId(String transactionId) {
            entity.setTransactionId(transactionId);
            return this;
        }

        @Override
        public CreditInfoUpdater setConfident(Boolean confident) {
            entity.setConfident(confident);
            return this;
        }

        @Override
        public CreditInfoEntity save() {
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

    private class CreditInfoQueryImpl implements CreditInfoQuery {

        private DetachedCriteria criteria = DetachedCriteria.forClass(CreditInfoEntityImpl.class);

        @Override
        public CreditInfoQuery typeIs(CreditInfoEntityType type) {
            criteria.add(Restrictions.eq("type", type));
            return this;
        }

        @Override
        public CreditInfoQuery checkIdOrNameContains(String checkIdOrName) {
            criteria.add(Restrictions.or(
                Restrictions.ilike("checkId", checkIdOrName, MatchMode.ANYWHERE),
                Restrictions.ilike("name", checkIdOrName, MatchMode.ANYWHERE))
            );
            return this;
        }

        @Override
        public CreditInfoQuery registrationTimeExists() {
            criteria.add(Restrictions.isNotNull("registrationTime"));
            return this;
        }

        @Override
        public CreditInfoQuery confident() {
            criteria.add(Restrictions.eq("confident", true));
            return this;
        }

        @Override
        public CreditInfoQuery orderByRegistrationTime(boolean desc) {
            criteria.addOrder(desc ? Order.desc("registrationTime") : Order.asc("registrationTime"));
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public List<CreditInfoEntity> list() {
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
        public List<CreditInfoEntity> list(int offset, int limit) {
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
