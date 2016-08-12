package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/8/11.
 */
public interface AttachmentUpdater extends Updater<AttachmentEntity> {

    AttachmentUpdater setId(String id);

    AttachmentUpdater setName(String name);

    AttachmentUpdater setContentType(String contentType);

    AttachmentUpdater setPath(String path);
}
