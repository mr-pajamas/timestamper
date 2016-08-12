package org.nelect.timestamper.internal.persistence.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.criterion.MatchMode;
import org.nelect.timestamper.internal.persistence.*;

/**
 * Created by Michael on 2016/7/13.
 */
class IndividualInfoManagerImpl implements IndividualInfoManager {

    private ContextImpl context;

    IndividualInfoManagerImpl(ContextImpl context) {
        this.context = context;
    }

    @Override
    public IndividualInfoUpdater newUpdater() {
        return new IndividualInfoUpdaterImpl();
    }

    @Override
    public IndividualInfoUpdater newUpdater(String id) {
        return new IndividualInfoUpdaterImpl(id);
    }

    @Override
    public IndividualInfoUpdater newUpdater(IndividualInfoEntity entity) {
        return new IndividualInfoUpdaterImpl((IndividualInfoEntityImpl) entity);
    }

    @Override
    public IndividualInfoEntity get(String id) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return session.get(IndividualInfoEntityImpl.class, id);
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public IndividualInfoEntity getByUserId(String userId) {
        return get(userId);
    }

    @Override
    public IndividualInfoEntity getByIdCardNumber(String idCardNumber) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return (IndividualInfoEntity) session.createCriteria(IndividualInfoEntityImpl.class)
                .add(Restrictions.eq("idCardNumber", idCardNumber))
                .uniqueResult();
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public IndividualInfoQuery newQuery() {
        return new IndividualInfoQueryImpl();
    }

    private class IndividualInfoUpdaterImpl implements IndividualInfoUpdater {

        private IndividualInfoEntityImpl entity;

        private IndividualInfoUpdaterImpl() {
            this.entity = new IndividualInfoEntityImpl();
        }

        private IndividualInfoUpdaterImpl(String id) {
            this.entity = new IndividualInfoEntityImpl();
            entity.setUserId(id);
        }

        private IndividualInfoUpdaterImpl(IndividualInfoEntityImpl entity) {
            this.entity = entity;
        }

        @Override
        public IndividualInfoUpdater setUserId(String userId) {
            entity.setUserId(userId);
            return this;
        }

        @Override
        public IndividualInfoUpdater setName(String name) {
            entity.setName(name);
            return this;
        }

        @Override
        public IndividualInfoUpdater setIdCardNumber(String idCardNumber) {
            entity.setIdCardNumber(idCardNumber);
            return this;
        }

/*
        @Override
        public IndividualInfoUpdater setIdCardFrontPictureContentType(String idCardFrontPictureContentType) {
            entity.setIdCardFrontPictureContentType(idCardFrontPictureContentType);
            return this;
        }

        @Override
        public IndividualInfoUpdater setIdCardFrontPicturePath(String idCardFrontPicturePath) {
            entity.setIdCardFrontPicturePath(idCardFrontPicturePath);
            return this;
        }

        @Override
        public IndividualInfoUpdater setIdCardBackPictureContentType(String idCardBackPictureContentType) {
            entity.setIdCardBackPictureContentType(idCardBackPictureContentType);
            return this;
        }

        @Override
        public IndividualInfoUpdater setIdCardBackPicturePath(String idCardBackPicturePath) {
            entity.setIdCardBackPicturePath(idCardBackPicturePath);
            return this;
        }
*/

        @Override
        public IndividualInfoUpdater setIdCardFrontPicture(String idCardFrontPicture) {
            entity.setIdCardFrontPicture(idCardFrontPicture);
            return this;
        }

        @Override
        public IndividualInfoUpdater setIdCardBackPicture(String idCardBackPicture) {
            entity.setIdCardBackPicture(idCardBackPicture);
            return this;
        }

        @Override
        public IndividualInfoEntity save() {
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

    private class IndividualInfoQueryImpl implements IndividualInfoQuery {

        private DetachedCriteria criteria = DetachedCriteria.forClass(IndividualInfoEntityImpl.class);

        @Override
        public IndividualInfoQuery nameContains(String name) {
            criteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public List<IndividualInfoEntity> list() {
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
        public List<IndividualInfoEntity> list(int offset, int limit) {
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
