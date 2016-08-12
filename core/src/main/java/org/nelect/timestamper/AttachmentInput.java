package org.nelect.timestamper;

import java.io.IOException;
import java.io.InputStream;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Michael on 2016/8/11.
 */
public interface AttachmentInput {

    @NotBlank(message = "附件文件名不可为空")
    String getName();

    @NotBlank(message = "附件文件MIME类型不可为空")
    String getContentType();

    InputStream openStream() throws IOException;
}
