package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/8/11.
 */
public interface AttachmentEntity extends Entity<String> {

    String getId();

    String getName();

    String getContentType();

    String getPath();
}
