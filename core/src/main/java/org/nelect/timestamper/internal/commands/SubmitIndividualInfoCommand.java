package org.nelect.timestamper.internal.commands;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.nelect.timestamper.IndividualInput;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.interceptors.TransactionInterceptor;
import org.nelect.timestamper.internal.persistence.UserEntity;

/**
 * Created by Michael on 2016/7/6.
 */
@Privileged
@TransactionInterceptor.Transactional
public class SubmitIndividualInfoCommand implements Command<Void> {

    @NotNull(message = "个人身份信息不可为空")
    @Valid
    private IndividualInput input;

    public SubmitIndividualInfoCommand(IndividualInput input) {
        this.input = input;
    }

    @Override
    public Void doExecute(CommandContext context) throws TimestamperException {
        // 1. 检查是否已经填写身份信息
        UserEntity entity = context.getPersistenceContext().getUserManager().get(context.getPrincipal().getId());
        if (entity.getIdentityType() == UserEntity.IdentityType.ORGANIZATION)
            throw new TimestamperException("企业账号不能变更为个人账号");


//        IndividualUserEntity individualUserEntity = context.getPersistenceContext().getIndividualUserManager().get(entity.getId());
//        System.out.println(individualUserEntity == null);

        // 2. 存储认证信息
/*
        context.getPersistenceContext().getIndividualUserManager().newUpdater()
            .fromUserEntity(entity)
            .setName(input.getName())
            .setIdCardNumber(input.getIdCardNumber())
            .setIdCardFrontPictureContentType(input.getIdCardFrontPicture().getContentType())
            .setIdCardFrontPicturePath(input.getIdCardFrontPicture().getFile().getAbsolutePath())
            .setIdCardBackPictureContentType(input.getIdCardBackPicture().getContentType())
            .setIdCardBackPicturePath(input.getIdCardBackPicture().getFile().getAbsolutePath())
            .save();
*/
        context.getPersistenceContext().getIndividualInfoManager().newUpdater()
            .setUserId(entity.getId())
            .setName(input.getName())
            .setIdCardNumber(input.getIdCardNumber())
//            .setIdCardFrontPictureContentType(input.getIdCardFrontPicture().getContentType())
//            .setIdCardFrontPicturePath(input.getIdCardFrontPicture().getFile().getAbsolutePath())
//            .setIdCardBackPictureContentType(input.getIdCardBackPicture().getContentType())
//            .setIdCardBackPicturePath(input.getIdCardBackPicture().getFile().getAbsolutePath())
            .setIdCardFrontPicture(input.getIdCardFrontPicture())
            .setIdCardBackPicture(input.getIdCardBackPicture())
            .save();

        context.getPersistenceContext().getUserManager().newUpdater(entity)
            .setIdentityType(UserEntity.IdentityType.INDIVIDUAL)
            .setVerificationTime(null)
            .setVerificationFailReasons(null)
            .save();

        return null;
    }
}
