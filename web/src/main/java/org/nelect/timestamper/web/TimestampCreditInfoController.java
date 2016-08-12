package org.nelect.timestamper.web;

import java.io.*;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.nelect.timestamper.*;
import org.nelect.timestamper.partner.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Michael on 2016/6/6.
 */
@Controller
@RequestMapping("/timestamp/credit-info")
public class TimestampCreditInfoController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET)
    public String showForm() {
        return "timestamp-credit-info";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView timestampCreditInfo(@Valid final CreditInfoForm creditInfoForm)
        throws IOException {

        final MultipartFile attachment = creditInfoForm.getAttachment();

        if (attachment.isEmpty()) return new ModelAndView("timestamp-credit-info");  // TODO: 需要修改

        Session session = sessionFactory.newSession();

        AttachmentService attachmentService = session.getAttachmentService();

        final String attachmentId = attachmentService.upload(new AttachmentInput() {

            @Override
            public String getName() {
                return FilenameUtils.getName(attachment.getOriginalFilename());
            }

            @Override
            public String getContentType() {
                return attachment.getContentType();
            }

            @Override
            public InputStream openStream() throws IOException {
                return attachment.getInputStream();
            }
        });

/*
        final File transferred = new File(timestamperConfig.getProperty("attachment.directory"), idGenerator.generateId())
            .getCanonicalFile();
        FileUtils.forceMkdirParent(transferred);
        attachmentFile.transferTo(transferred);

        final String attachmentName = FilenameUtils.getName(attachmentFile.getOriginalFilename());
        final String attachmentContentType = attachmentFile.getContentType();
*/

        CreditworthinessService creditworthinessService = session.getCreditworthinessService();

        CreditInfoInput input = new CreditInfoInput() {

            @Override
            public String getCheckId() {
                return creditInfoForm.getCheckId();
            }

            @Override
            public String getName() {
                return creditInfoForm.getName();
            }

            @Override
            public String getDetails() {
                return creditInfoForm.getDetails();
            }

/*
            @Override
            public Attachment getAttachment() {
                return new Attachment() {

                    @Override
                    public String getName() {
                        return attachmentName;
                    }

                    @Override
                    public String getContentType() {
                        return attachmentContentType;
                    }

                    @Override
                    public File getFile() {
                        return transferred;
                    }
                };
            }
*/

            @Override
            public String getAttachmentId() {
                return attachmentId;
            }

            @Override
            public String getPrincipalName() {
                return creditInfoForm.getPrincipalName();
            }
        };

        CreditInfo creditInfo;

        if (creditInfoForm.getType() == CreditInfoForm.Type.MANUFACTURER) {
            creditInfo = creditworthinessService.timestampManufacturer(input);
        } else {
            creditInfo = creditworthinessService.timestampProduct(input);
        }

        ModelAndView mav = new ModelAndView("timestamp-success");
        mav.addObject("creditInfo", creditInfo);
        return mav;
    }
}
