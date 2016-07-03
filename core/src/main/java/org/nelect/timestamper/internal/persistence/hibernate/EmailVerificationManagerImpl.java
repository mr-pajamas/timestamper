package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.nelect.timestamper.internal.persistence.*;

/**
 * Created by Michael on 2016/7/3.
 */
class EmailVerificationManagerImpl implements EmailVerificationManager {

    private ContextImpl context;

    EmailVerificationManagerImpl(ContextImpl context) {
        this.context = context;
    }

    @Override
    public EmailVerificationUpdater newUpdater() {
        return new EmailVerificationUpdaterImpl();
    }

    @Override
    public EmailVerificationUpdater newUpdater(String id) {
        return new EmailVerificationUpdaterImpl(id);
    }

    @Override
    public EmailVerificationUpdater newUpdater(EmailVerificationEntity entity) {
        return new EmailVerificationUpdaterImpl((EmailVerificationEntityImpl) entity);
    }

    @Override
    public EmailVerificationEntity get(String id) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return session.get(EmailVerificationEntity.class, id);
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public EmailVerificationEntity getByEmail(String email) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (EmailVerificationEntity) session.createCriteria(EmailVerificationEntity.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public Query<EmailVerificationEntity> newQuery() {
        throw new UnsupportedOperationException("电子邮箱验证实体不支持查询功能");
    }

    private class EmailVerificationUpdaterImpl implements EmailVerificationUpdater {

        private EmailVerificationEntityImpl entity;

        private EmailVerificationUpdaterImpl() {
            this.entity = new EmailVerificationEntityImpl();
        }

        private EmailVerificationUpdaterImpl(String id) {
            this.entity = new EmailVerificationEntityImpl();
            entity.setId(id);
        }

        private EmailVerificationUpdaterImpl(EmailVerificationEntityImpl entity) {
            this.entity = entity;
        }

        @Override
        public EmailVerificationUpdater setEmail(String email) {
            entity.setEmail(email);
            return this;
        }

        @Override
        public EmailVerificationUpdater setCode(String code) {
            entity.setCode(code);
            return this;
        }

        @Override
        public EmailVerificationUpdater setExpiration(Date expiration) {
            entity.setExpiration(expiration);
            return this;
        }

        @Override
        public EmailVerificationUpdater setUsed(Boolean used) {
            entity.setUsed(used);
            return this;
        }

        @Override
        public EmailVerificationEntity save() {
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
}
