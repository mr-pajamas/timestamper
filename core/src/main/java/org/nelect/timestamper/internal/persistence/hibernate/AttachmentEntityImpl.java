package org.nelect.timestamper.internal.persistence.hibernate;

import org.nelect.timestamper.internal.persistence.AttachmentEntity;

/**
 * Created by Michael on 2016/8/11.
 */
class AttachmentEntityImpl implements AttachmentEntity {

    private String id;

    private String name;
    private String contentType;
    private String path;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttachmentEntityImpl that = (AttachmentEntityImpl) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!contentType.equals(that.contentType)) return false;
        return path.equals(that.path);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + contentType.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }
}
