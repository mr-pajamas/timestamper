package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.criterion.MatchMode;
import org.nelect.timestamper.internal.persistence.*;

/**
 * Created by Michael on 2016/7/3.
 */
class CertificateManagerImpl implements CertificateManager {

    private ContextImpl context;

    CertificateManagerImpl(ContextImpl context) {
        this.context = context;
    }

    @Override
    public CertificateUpdater newUpdater() {
        return new CertificateUpdaterImpl();
    }

    @Override
    public CertificateUpdater newUpdater(String id) {
        return new CertificateUpdaterImpl(id);
    }

    @Override
    public CertificateUpdater newUpdater(CertificateEntity entity) {
        return new CertificateUpdaterImpl((CertificateEntityImpl) entity);
    }

    @Override
    public CertificateEntity get(String id) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return session.get(CertificateEntity.class, id);
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public CertificateEntity getByTransactionId(String transactionId) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (CertificateEntity) session.createCriteria(CertificateEntity.class)
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
    public CertificateQuery newQuery() {
        return new CertificateQueryImpl();
    }

    private class CertificateUpdaterImpl implements CertificateUpdater {

        private CertificateEntityImpl entity;

        private CertificateUpdaterImpl() {
            this.entity = new CertificateEntityImpl();
        }

        private CertificateUpdaterImpl(String id) {
            this.entity = new CertificateEntityImpl();
            entity.setId(id);
        }

        private CertificateUpdaterImpl(CertificateEntityImpl entity) {
            this.entity = entity;
        }

        @Override
        public CertificateUpdater setTitle(String title) {
            entity.setTitle(title);
            return this;
        }

        @Override
        public CertificateUpdater setPrincipalId(String principalId) {
            entity.setPrincipalId(principalId);
            return this;
        }

        @Override
        public CertificateUpdater setRegistrationTime(Date registrationTime) {
            entity.setRegistrationTime(registrationTime);
            return this;
        }

/*
        @Override
        public CertificateUpdater setAttachmentName(String attachmentName) {
            entity.setAttachmentName(attachmentName);
            return this;
        }

        @Override
        public CertificateUpdater setAttachmentContentType(String attachmentContentType) {
            entity.setAttachmentContentType(attachmentContentType);
            return this;
        }

        @Override
        public CertificateUpdater setAttachmentPath(String attachmentPath) {
            entity.setAttachmentPath(attachmentPath);
            return this;
        }
*/

        @Override
        public CertificateUpdater setAttachmentId(String attachmentId) {
            entity.setAttachmentId(attachmentId);
            return this;
        }

        @Override
        public CertificateUpdater setDigest(String digest) {
            entity.setDigest(digest);
            return this;
        }

        @Override
        public CertificateUpdater setTransactionId(String transactionId) {
            entity.setTransactionId(transactionId);
            return this;
        }

        @Override
        public CertificateUpdater setConfident(Boolean confident) {
            entity.setConfident(confident);
            return this;
        }

        @Override
        public CertificateEntity save() {
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

    private class CertificateQueryImpl implements CertificateQuery {

        private DetachedCriteria criteria = DetachedCriteria.forClass(CertificateEntityImpl.class);

        @Override
        public CertificateQuery titleContains(String title) {
            criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
            return this;
        }

        @Override
        public CertificateQuery principalIdIs(String principalId) {
            criteria.add(Restrictions.eq("principalId", principalId));
            return this;
        }

        @Override
        public CertificateQuery registrationTimeExists() {
            criteria.add(Restrictions.isNotNull("registrationTime"));
            return this;
        }

        @Override
        public CertificateQuery confident() {
            criteria.add(Restrictions.eq("confident", true));
            return this;
        }

        @Override
        public CertificateQuery orderByRegistrationTime(boolean desc) {
            criteria.addOrder(desc ? Order.desc("registrationTime") : Order.asc("registrationTime"));
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public List<CertificateEntity> list() {
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
        public List<CertificateEntity> list(int offset, int limit) {
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
