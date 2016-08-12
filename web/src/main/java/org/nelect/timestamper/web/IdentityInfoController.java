package org.nelect.timestamper.web;

import java.io.*;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.nelect.timestamper.*;
import org.nelect.timestamper.user.IdentityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.nelect.timestamper.web.MimeTypeTable.getExtension;

/**
 * Created by Michael on 2016/7/6.
 */
@Controller
@RequestMapping("/my-identity")
public class IdentityInfoController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET)
    public String route(@SessionAttribute("principal") DiscriminatedPrincipal discriminatedPrincipal, Model model) {
        Principal principal = discriminatedPrincipal.getValue();
        if (principal instanceof VerifiedIndividual) {
            return "individual_verified";
        } else if (principal instanceof VerifiedOrganization) {
            return "organization_verified";
        } else if (principal instanceof Individual) {
            IdentityService identityService = sessionFactory.newSession(discriminatedPrincipal.getValue()).getIdentityService();
            String failReasons = identityService.getVerificationFailReasons();
            if (failReasons != null) {
                model.addAttribute("failReasons", failReasons);
                return "individual_not_verified";
            } else {
                return "individual_verifying";
            }
        } else if (principal instanceof Organization) {
            IdentityService identityService = sessionFactory.newSession(discriminatedPrincipal.getValue()).getIdentityService();
            String failReasons = identityService.getVerificationFailReasons();
            if (failReasons != null) {
                model.addAttribute("failReasons", failReasons);
                return "organization_not_verified";
            } else {
                return "organization_verifying";
            }
        } else {
            return "verify_identity";
        }
    }

    @RequestMapping(method = RequestMethod.POST, params = "type=individual")
    public String submitIndividualInfo(@SessionAttribute("principal") DiscriminatedPrincipal discriminatedPrincipal,
                                       @Valid final IndividualInfoForm individualInfoForm)
        throws IOException {
        // 1. 两张照片empty判断
        // 2.
        final String idCardNo = individualInfoForm.getIdCardNo();
        final MultipartFile idCardFrontPicFile = individualInfoForm.getIdCardFrontPic();
        final MultipartFile idCardBackPicFile = individualInfoForm.getIdCardBackPic();

        Session session = sessionFactory.newSession(discriminatedPrincipal.getValue());

        AttachmentService attachmentService = session.getAttachmentService();

        final String idCardFrontPicture = attachmentService.upload(new AttachmentInput() {

            @Override
            public String getName() {
                return "身份证正面照片" + getExtension(getContentType());
            }

            @Override
            public String getContentType() {
                return idCardFrontPicFile.getContentType();
            }

            @Override
            public InputStream openStream() throws IOException {
                return idCardFrontPicFile.getInputStream();
            }
        });

        final String idCardBackPicture = attachmentService.upload(new AttachmentInput() {

            @Override
            public String getName() {
                return "身份证反面照片" + getExtension(getContentType());
            }

            @Override
            public String getContentType() {
                return idCardBackPicFile.getContentType();
            }

            @Override
            public InputStream openStream() throws IOException {
                return idCardBackPicFile.getInputStream();
            }
        });

/*
        File idCardDir = new File(timestamperConfig.getProperty("idcard.directory"));
        FileUtils.forceMkdir(idCardDir);

        final File idCardFrontPicTransferred = new File(idCardDir,
            idCardNo + "-1." + FilenameUtils.getExtension(idCardFrontPicFile.getOriginalFilename()))
            .getCanonicalFile();
        final File idCardBackPicTransferred = new File(idCardDir,
            idCardNo + "-2." + FilenameUtils.getExtension(idCardBackPicFile.getOriginalFilename()))
            .getCanonicalFile();

        idCardFrontPicFile.transferTo(idCardFrontPicTransferred);
        idCardBackPicFile.transferTo(idCardBackPicTransferred);
*/

        IdentityService identityService = session.getIdentityService();
        IndividualInput input = new IndividualInput() {

            @Override
            public String getName() {
                return individualInfoForm.getName();
            }

            @Override
            public String getIdCardNumber() {
                return idCardNo;
            }
/*
            @Override
            public Attachment getIdCardFrontPicture() {
                return new Attachment() {

                    @Override
                    public String getName() {
                        return idCardFrontPicTransferred.getName();
                    }

                    @Override
                    public String getContentType() {
                        return idCardFrontPicFile.getContentType();
                    }

                    @Override
                    public File getFile() {
                        return idCardFrontPicTransferred;
                    }
                };
            }

            @Override
            public Attachment getIdCardBackPicture() {
                return new Attachment() {

                    @Override
                    public String getName() {
                        return idCardBackPicTransferred.getName();
                    }

                    @Override
                    public String getContentType() {
                        return idCardBackPicFile.getContentType();
                    }

                    @Override
                    public File getFile() {
                        return idCardBackPicTransferred;
                    }
                };
            }
            */

            @Override
            public String getIdCardFrontPicture() {
                return idCardFrontPicture;
            }

            @Override
            public String getIdCardBackPicture() {
                return idCardBackPicture;
            }
        };

        identityService.submitIndividualInfo(input);

        return "redirect:/my-identity";
    }

    @RequestMapping(method = RequestMethod.POST, params = "type=organization")
    public String submitOrganizationInfo(@SessionAttribute("principal") DiscriminatedPrincipal discriminatedPrincipal,
                                         @Valid final OrganizationInfoForm organizationInfoForm)
        throws IOException {

        final String registrationNo = organizationInfoForm.getRegistrationNo();
        final MultipartFile licensePicFile = organizationInfoForm.getLicensePic();

        Session session = sessionFactory.newSession(discriminatedPrincipal.getValue());

        AttachmentService attachmentService = session.getAttachmentService();

        final String licensePicture = attachmentService.upload(new AttachmentInput() {

            @Override
            public String getName() {
                return "企业营业执照照片" + getExtension(getContentType());
            }

            @Override
            public String getContentType() {
                return licensePicFile.getContentType();
            }

            @Override
            public InputStream openStream() throws IOException {
                return licensePicFile.getInputStream();
            }
        });

/*
        File licenseDir = new File(timestamperConfig.getProperty("license.directory"));
        FileUtils.forceMkdir(licenseDir);

        final File licensePicTransferred = new File(licenseDir,
            registrationNo + "." + FilenameUtils.getExtension(licensePicFile.getOriginalFilename()))
            .getCanonicalFile();

        licensePicFile.transferTo(licensePicTransferred);
*/

        IdentityService identityService = session.getIdentityService();
        OrganizationInput input = new OrganizationInput() {

            @Override
            public String getName() {
                return organizationInfoForm.getName();
            }

            @Override
            public String getRegistrationNumber() {
                return registrationNo;
            }

/*            @Override
            public Attachment getBusinessLicensePicture() {
                return new Attachment() {

                    @Override
                    public String getName() {
                        return licensePicTransferred.getName();
                    }

                    @Override
                    public String getContentType() {
                        return licensePicFile.getContentType();
                    }

                    @Override
                    public File getFile() {
                        return licensePicTransferred;
                    }
                };
            }*/

            @Override
            public String getBusinessLicensePicture() {
                return licensePicture;
            }
        };

        identityService.submitOrganizationInfo(input);

        return "redirect:/my-identity";
    }
}
