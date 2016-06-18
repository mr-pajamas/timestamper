package org.nelect.timestamper.partner;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Michael on 2016/6/6.
 */
public interface EInvoiceInput {

    @NotBlank(message = "查验ID不可为空")
    String getCheckId();

    @NotBlank(message = "证件编号不可为空")
    String getCertNumber();

    //Attachment getAttachment();

    @NotBlank(message = "文件指纹不可为空")
    String getChecksum();

    @NotBlank(message = "存证主体不可为空")
    String getPrincipalName();
}
