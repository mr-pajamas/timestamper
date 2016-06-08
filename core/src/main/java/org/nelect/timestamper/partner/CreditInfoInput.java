package org.nelect.timestamper.partner;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.nelect.timestamper.Attachment;

/**
 * Created by Michael on 2016/6/6.
 */
public interface CreditInfoInput {

    @NotBlank(message = "查验ID不可为空")
    String getCheckId();

    @NotBlank(message = "名称不可为空")
    String getName();

    @NotBlank(message = "详情不可为空")
    String getDetails();

    @NotNull(message = "存证附件未提供")
    @Valid
    Attachment getAttachment();

    @NotBlank(message = "存证主体不可为空")
    String getPrincipalName();
}
