package org.nelect.timestamper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Michael on 2016/7/3.
 */
public interface CertificateInput {

    @NotBlank(message = "文件标题不可为空")
    String getTitle();

    @NotNull(message = "存证附件未提供")
    @Valid
    Attachment getAttachment();
}
