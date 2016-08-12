package org.nelect.timestamper;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Michael on 2016/7/6.
 */
public interface OrganizationInput {

    @NotBlank(message = "企业名称不可为空")
    String getName();

    @NotBlank(message = "工商注册号不可为空")
    String getRegistrationNumber();

/*    @NotNull(message = "营业执照扫描件未提供")
    @Valid
    Attachment getBusinessLicensePicture();
    */
    @NotBlank(message = "营业执照扫描件未提供")
    String getBusinessLicensePicture();
}
