package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.nelect.timestamper.internal.persistence.*;

/**
 * Created by Michael on 2016/7/5.
 */
class UserManagerImpl implements UserManager {

    private ContextImpl context;

    UserManagerImpl(ContextImpl context) {
        this.context = context;
    }

    @Override
    public UserUpdater newUpdater() {
        return new UserUpdaterImpl();
    }

    @Override
    public UserUpdater newUpdater(String id) {
        return new UserUpdaterImpl(id);
    }

    @Override
    public UserUpdater newUpdater(UserEntity entity) {
        return new UserUpdaterImpl((UserEntityImpl) entity);
    }

    @Override
    public UserEntity get(String id) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return session.get(UserEntityImpl.class, id);
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public UserEntity getByEmail(String email) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (UserEntity) session.createCriteria(UserEntityImpl.class)
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
    public UserEntity getByMobile(String mobile) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (UserEntity) session.createCriteria(UserEntityImpl.class)
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
    public UserQuery newQuery() {
        return new UserQueryImpl();
    }

    private class UserUpdaterImpl implements UserUpdater {

        private UserEntityImpl entity;

        private UserUpdaterImpl() {
            this.entity = new UserEntityImpl();
        }

        private UserUpdaterImpl(String id) {
            this.entity = new UserEntityImpl();
            entity.setId(id);
        }

        private UserUpdaterImpl(UserEntityImpl entity) {
            this.entity = entity;
        }

        @Override
        public UserUpdater setEmail(String email) {
            entity.setEmail(email);
            return this;
        }

        @Override
        public UserUpdater setMobile(String mobile) {
            entity.setMobile(mobile);
            return this;
        }

        @Override
        public UserUpdater setPassword(String password) {
            entity.setPassword(password);
            return this;
        }

        @Override
        public UserUpdater setCreationTime(Date creationTime) {
            entity.setCreationTime(creationTime);
            return this;
        }

        @Override
        public UserUpdater setIdentityType(UserEntity.IdentityType type) {
            entity.setIdentityType(type);
            return this;
        }

        @Override
        public UserUpdater setVerificationTime(Date verificationTime) {
            entity.setVerificationTime(verificationTime);
            return this;
        }

        @Override
        public UserUpdater setVerificationFailReasons(String verificationFailReasons) {
            entity.setVerificationFailReasons(verificationFailReasons);
            return this;
        }

        @Override
        public UserUpdater setCertificateCount(Integer certificateCount) {
            entity.setCertificateCount(certificateCount);
            return this;
        }

        @Override
        public UserUpdater setBalance(Integer balance) {
            entity.setBalance(balance);
            return this;
        }

        @Override
        public UserEntity save() {
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

    private class UserQueryImpl implements UserQuery {

        private DetachedCriteria criteria = DetachedCriteria.forClass(UserEntityImpl.class);

        @Override
        public UserQuery identified(boolean individualOrOrganization) {
            criteria.add(Restrictions.eq("identityType", individualOrOrganization ? UserEntity.IdentityType.INDIVIDUAL : UserEntity.IdentityType.ORGANIZATION));
            return this;
        }

        @Override
        public UserQuery verificationTimeExists(boolean exists) {
            if (exists)
                criteria.add(Restrictions.isNotNull("verificationTime"));
            else
                criteria.add(Restrictions.isNull("verificationTime"));
            return this;
        }

        @Override
        public UserQuery orderByCreationTime(boolean desc) {
            criteria.addOrder(desc ? Order.desc("creationTime") : Order.asc("creationTime"));
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public List<UserEntity> list() {
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
        public List<UserEntity> list(int offset, int limit) {
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
