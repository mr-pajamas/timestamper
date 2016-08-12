package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.criterion.MatchMode;
import org.nelect.timestamper.internal.persistence.*;

/**
 * Created by Michael on 2016/7/13.
 */
class OrganizationInfoManagerImpl implements OrganizationInfoManager {

    private ContextImpl context;

    OrganizationInfoManagerImpl(ContextImpl context) {
        this.context = context;
    }

    @Override
    public OrganizationInfoUpdater newUpdater() {
        return new OrganizationInfoUpdaterImpl();
    }

    @Override
    public OrganizationInfoUpdater newUpdater(String id) {
        return new OrganizationInfoUpdaterImpl(id);
    }

    @Override
    public OrganizationInfoUpdater newUpdater(OrganizationInfoEntity entity) {
        return new OrganizationInfoUpdaterImpl((OrganizationInfoEntityImpl) entity);
    }

    @Override
    public OrganizationInfoEntity get(String id) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return session.get(OrganizationInfoEntityImpl.class, id);
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public OrganizationInfoEntity getByUserId(String userId) {
        return get(userId);
    }

    @Override
    public OrganizationInfoEntity getByRegistrationNumber(String registrationNumber) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (OrganizationInfoEntity) session.createCriteria(OrganizationInfoEntityImpl.class)
                .add(Restrictions.eq("registrationNumber", registrationNumber))
                .uniqueResult();
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public OrganizationInfoQuery newQuery() {
        return new OrganizationInfoQueryImpl();
    }

    private class OrganizationInfoUpdaterImpl implements OrganizationInfoUpdater {

        private OrganizationInfoEntityImpl entity;

        private OrganizationInfoUpdaterImpl() {
            this.entity = new OrganizationInfoEntityImpl();
        }

        private OrganizationInfoUpdaterImpl(String id) {
            this.entity = new OrganizationInfoEntityImpl();
            entity.setUserId(id);
        }

        private OrganizationInfoUpdaterImpl(OrganizationInfoEntityImpl entity) {
            this.entity = entity;
        }

        @Override
        public OrganizationInfoUpdater setUserId(String userId) {
            entity.setUserId(userId);
            return this;
        }

        @Override
        public OrganizationInfoUpdater setName(String name) {
            entity.setName(name);
            return this;
        }

        @Override
        public OrganizationInfoUpdater setRegistrationNumber(String registrationNumber) {
            entity.setRegistrationNumber(registrationNumber);
            return this;
        }

/*
        @Override
        public OrganizationInfoUpdater setBusinessLicensePictureContentType(String businessLicensePictureContentType) {
            entity.setBusinessLicensePictureContentType(businessLicensePictureContentType);
            return this;
        }

        @Override
        public OrganizationInfoUpdater setBusinessLicensePicturePath(String businessLicensePicturePath) {
            entity.setBusinessLicensePicturePath(businessLicensePicturePath);
            return this;
        }
*/

        @Override
        public OrganizationInfoUpdater setBusinessLicensePicture(String businessLicensePicture) {
            entity.setBusinessLicensePicture(businessLicensePicture);
            return this;
        }

        @Override
        public OrganizationInfoEntity save() {
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

    private class OrganizationInfoQueryImpl implements OrganizationInfoQuery {

        private DetachedCriteria criteria = DetachedCriteria.forClass(OrganizationInfoEntityImpl.class);

        @Override
        public OrganizationInfoQuery nameContains(String name) {
            criteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public List<OrganizationInfoEntity> list() {
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
        public List<OrganizationInfoEntity> list(int offset, int limit) {
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
