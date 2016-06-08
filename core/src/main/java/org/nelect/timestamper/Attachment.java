package org.nelect.timestamper;

import java.io.File;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Michael on 2016/5/30.
 */
public interface Attachment {

    @NotBlank(message = "附件文件名不可为空")
    String getName();

    @NotBlank(message = "附件文件MIME类型不可为空")
    String getContentType();

    @NotNull(message = "附件未指定")
    File getFile();
}
