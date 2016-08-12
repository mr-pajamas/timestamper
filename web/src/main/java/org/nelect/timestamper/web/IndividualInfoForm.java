package org.nelect.timestamper.web;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Michael on 2016/7/8.
 */
public class IndividualInfoForm {

    private String name;
    private String idCardNo;
    private MultipartFile idCardFrontPic;
    private MultipartFile idCardBackPic;

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    @NotNull
    public MultipartFile getIdCardFrontPic() {
        return idCardFrontPic;
    }

    public void setIdCardFrontPic(MultipartFile idCardFrontPic) {
        this.idCardFrontPic = idCardFrontPic;
    }

    @NotNull
    public MultipartFile getIdCardBackPic() {
        return idCardBackPic;
    }

    public void setIdCardBackPic(MultipartFile idCardBackPic) {
        this.idCardBackPic = idCardBackPic;
    }
}
