package org.nelect.timestamper;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Michael on 2016/7/6.
 */
public interface IndividualInput {

    @NotBlank(message = "姓名不可为空")
    String getName();

    @NotBlank(message = "身份证号码不可为空")
    String getIdCardNumber();

/*    @NotNull(message = "身份证正面照未提供")
    @Valid
    Attachment getIdCardFrontPicture();
    */
    @NotBlank(message = "身份证正面照未提供")
    String getIdCardFrontPicture();

/*    @NotNull(message = "身份证背面照未提供")
    @Valid
    Attachment getIdCardBackPicture();
    */
    @NotBlank(message = "身份证背面照未提供")
    String getIdCardBackPicture();
}
