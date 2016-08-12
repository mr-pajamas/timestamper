package org.nelect.timestamper.web;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Michael on 2016/7/13.
 */
public class OrganizationInfoForm {

    private String name;
    private String registrationNo;
    private MultipartFile licensePic;

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    @NotNull
    public MultipartFile getLicensePic() {
        return licensePic;
    }

    public void setLicensePic(MultipartFile licensePic) {
        this.licensePic = licensePic;
    }
}
