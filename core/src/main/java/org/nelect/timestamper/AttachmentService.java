package org.nelect.timestamper;

/**
 * Created by Michael on 2016/8/10.
 */
public interface AttachmentService {

    String upload(AttachmentInput input);

    Attachment get(String id);
}
