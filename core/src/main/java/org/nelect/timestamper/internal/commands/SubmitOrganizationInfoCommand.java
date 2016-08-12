package org.nelect.timestamper.internal.commands;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.nelect.timestamper.OrganizationInput;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.interceptors.TransactionInterceptor;
import org.nelect.timestamper.internal.persistence.UserEntity;

/**
 * Created by Michael on 2016/7/6.
 */
@Privileged
@TransactionInterceptor.Transactional
public class SubmitOrganizationInfoCommand implements Command<Void> {

    @NotNull(message = "企业认证信息不可为空")
    @Valid
    private OrganizationInput input;

    public SubmitOrganizationInfoCommand(OrganizationInput input) {
        this.input = input;
    }

    @Override
    public Void doExecute(CommandContext context) throws TimestamperException {

        // 1. 检查是否已经填写认证信息
        UserEntity entity = context.getPersistenceContext().getUserManager().get(context.getPrincipal().getId());
        if (entity.getIdentityType() == UserEntity.IdentityType.INDIVIDUAL)
            throw new TimestamperException("个人账号不能变更为企业账号");
//        if (entity instanceof IndividualUserEntity || entity instanceof OrganizationUserEntity)
//            throw new TimestamperException("认证信息已经提交，无法重复填写");

        // 2. 存储认证信息
/*
        context.getPersistenceContext().getOrganizationUserManager().newUpdater()
            .fromUserEntity(entity)
            .setName(input.getName())
            .setRegistrationNumber(input.getRegistrationNumber())
            .setBusinessLicensePictureContentType(input.getBusinessLicensePicture().getContentType())
            .setBusinessLicensePicturePath(input.getBusinessLicensePicture().getFile().getAbsolutePath())
            .save();
*/
        context.getPersistenceContext().getOrganizationInfoManager().newUpdater()
            .setUserId(entity.getId())
            .setName(input.getName())
            .setRegistrationNumber(input.getRegistrationNumber())
/*            .setBusinessLicensePictureContentType(input.getBusinessLicensePicture().getContentType())
            .setBusinessLicensePicturePath(input.getBusinessLicensePicture().getFile().getAbsolutePath())*/
            .setBusinessLicensePicture(input.getBusinessLicensePicture())
            .save();

        context.getPersistenceContext().getUserManager().newUpdater(entity)
            .setIdentityType(UserEntity.IdentityType.ORGANIZATION)
            .setVerificationTime(null)
            .setVerificationFailReasons(null)
            .save();

        return null;
    }
}
