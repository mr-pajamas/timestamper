package org.nelect.timestamper.internal;

import org.nelect.timestamper.Organization;
import org.nelect.timestamper.internal.persistence.OrganizationInfoEntity;
import org.nelect.timestamper.internal.persistence.UserEntity;

/**
 * Created by Michael on 2016/7/5.
 */
public class OrganizationImpl extends PrincipalImpl implements Organization {

    private OrganizationInfoEntity organizationInfoEntity;

    public OrganizationImpl(UserEntity userEntity, OrganizationInfoEntity organizationInfoEntity) {
        super(userEntity);
        this.organizationInfoEntity = organizationInfoEntity;
    }

    @Override
    public String getName() {
        return organizationInfoEntity.getName();
    }

    @Override
    public String getRegistrationNumber() {
        return organizationInfoEntity.getRegistrationNumber();
    }

/*    @Override
    public Attachment getBusinessLicensePicture() {
        return new Attachment() {

            @Override
            public String getName() {
                return "企业营业执照照片" + getExtension(getContentType());
            }

            @Override
            public String getContentType() {
                return organizationInfoEntity.getBusinessLicensePictureContentType();
            }

            @Override
            public File getFile() {
                return new File(organizationInfoEntity.getBusinessLicensePicturePath());
            }
        };
    }*/

    @Override
    public String getBusinessLicensePicture() {
        return organizationInfoEntity.getBusinessLicensePicture();
    }
}
