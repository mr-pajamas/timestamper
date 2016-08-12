package org.nelect.timestamper.internal.persistence.hibernate;

import org.hibernate.Session;
import org.nelect.timestamper.internal.persistence.*;

/**
 * Created by Michael on 2016/8/11.
 */
class AttachmentManagerImpl implements AttachmentManager {

    private ContextImpl context;

    AttachmentManagerImpl(ContextImpl context) {
        this.context = context;
    }

    @Override
    public AttachmentUpdater newUpdater() {
        return new AttachmentUpdaterImpl();
    }

    @Override
    public AttachmentUpdater newUpdater(String id) {
        return new AttachmentUpdaterImpl(id);
    }

    @Override
    public AttachmentUpdater newUpdater(AttachmentEntity entity) {
        return new AttachmentUpdaterImpl((AttachmentEntityImpl) entity);
    }

    @Override
    public AttachmentEntity get(String id) {
        boolean failed = false;
        Session session = context.getSession();
        try {
            return session.get(AttachmentEntity.class, id);
        } catch (RuntimeException re) {
            failed = true;
            throw re;
        } finally {
            context.releaseSession(failed);
        }
    }

    @Override
    public Query<AttachmentEntity> newQuery() {
        throw new UnsupportedOperationException("附件实体不支持查询功能");
    }

    private class AttachmentUpdaterImpl implements AttachmentUpdater {

        private AttachmentEntityImpl entity;

        private AttachmentUpdaterImpl() {
            this.entity = new AttachmentEntityImpl();
        }

        private AttachmentUpdaterImpl(String id) {
            this.entity = new AttachmentEntityImpl();
            entity.setId(id);
        }

        private AttachmentUpdaterImpl(AttachmentEntityImpl entity) {
            this.entity = entity;
        }

        @Override
        public AttachmentUpdater setId(String id) {
            entity.setId(id);
            return this;
        }

        @Override
        public AttachmentUpdater setName(String name) {
            entity.setName(name);
            return this;
        }

        @Override
        public AttachmentUpdater setContentType(String contentType) {
            entity.setContentType(contentType);
            return this;
        }

        @Override
        public AttachmentUpdater setPath(String path) {
            entity.setPath(path);
            return this;
        }

        @Override
        public AttachmentEntity save() {
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
