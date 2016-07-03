package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.nelect.timestamper.internal.persistence.*;

/**
 * Created by Michael on 2016/7/2.
 */
class MobileVerificationManagerImpl implements MobileVerificationManager {

    private ContextImpl context;

    MobileVerificationManagerImpl(ContextImpl context) {
        this.context = context;
    }

    @Override
    public MobileVerificationUpdater newUpdater() {
        return new MobileVerificationUpdaterImpl();
    }

    @Override
    public MobileVerificationUpdater newUpdater(String id) {
        return new MobileVerificationUpdaterImpl(id);
    }

    @Override
    public MobileVerificationUpdater newUpdater(MobileVerificationEntity entity) {
        return new MobileVerificationUpdaterImpl((MobileVerificationEntityImpl) entity);
    }

    @Override
    public MobileVerificationEntity get(String id) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return session.get(MobileVerificationEntity.class, id);
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public MobileVerificationEntity getByMobile(String mobile) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (MobileVerificationEntity) session.createCriteria(MobileVerificationEntity.class)
                .add(Restrictions.eq("mobile", mobile))
                .uniqueResult();
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public Query<MobileVerificationEntity> newQuery() {
        throw new UnsupportedOperationException("手机验证实体不支持查询功能");
    }

    private class MobileVerificationUpdaterImpl implements MobileVerificationUpdater {

        private MobileVerificationEntityImpl entity;

        private MobileVerificationUpdaterImpl() {
            this.entity = new MobileVerificationEntityImpl();
        }

        private MobileVerificationUpdaterImpl(String id) {
            this.entity = new MobileVerificationEntityImpl();
            entity.setId(id);
        }

        private MobileVerificationUpdaterImpl(MobileVerificationEntityImpl entity) {
            this.entity = entity;
        }

        @Override
        public MobileVerificationUpdater setMobile(String mobile) {
            entity.setMobile(mobile);
            return this;
        }

        @Override
        public MobileVerificationUpdater setCode(String code) {
            entity.setCode(code);
            return this;
        }

        @Override
        public MobileVerificationUpdater setExpiration(Date expiration) {
            entity.setExpiration(expiration);
            return this;
        }

        @Override
        public MobileVerificationUpdater setUsed(Boolean used) {
            entity.setUsed(used);
            return this;
        }

        @Override
        public MobileVerificationEntity save() {
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
