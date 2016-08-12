package org.nelect.timestamper.internal;

import org.nelect.timestamper.Individual;
import org.nelect.timestamper.internal.persistence.IndividualInfoEntity;
import org.nelect.timestamper.internal.persistence.UserEntity;

/**
 * Created by Michael on 2016/7/5.
 */
public class IndividualImpl extends PrincipalImpl implements Individual {

    private IndividualInfoEntity individualInfoEntity;

    public IndividualImpl(UserEntity userEntity, IndividualInfoEntity individualInfoEntity) {
        super(userEntity);
        this.individualInfoEntity = individualInfoEntity;
    }

    @Override
    public String getName() {
        return individualInfoEntity.getName();
    }

    @Override
    public String getIdCardNumber() {
        return individualInfoEntity.getIdCardNumber();
    }

/*    @Override
    public Attachment getIdCardFrontPicture() {
        return new Attachment() {

            @Override
            public String getName() {
                return "身份证正面照片" + getExtension(individualInfoEntity.getIdCardFrontPictureContentType());
            }

            @Override
            public String getContentType() {
                return individualInfoEntity.getIdCardFrontPictureContentType();
            }

            @Override
            public File getFile() {
                return new File(individualInfoEntity.getIdCardFrontPicturePath());
            }
        };
    }

    @Override
    public Attachment getIdCardBackPicture() {
        return new Attachment() {

            @Override
            public String getName() {
                return "身份证反面照片" + getExtension(getContentType());
            }

            @Override
            public String getContentType() {
                return individualInfoEntity.getIdCardBackPictureContentType();
            }

            @Override
            public File getFile() {
                return new File(individualInfoEntity.getIdCardBackPicturePath());
            }
        };
    }
    */

    @Override
    public String getIdCardFrontPicture() {
        return individualInfoEntity.getIdCardFrontPicture();
    }

    @Override
    public String getIdCardBackPicture() {
        return individualInfoEntity.getIdCardBackPicture();
    }
}
