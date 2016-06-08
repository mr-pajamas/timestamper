package org.nelect.timestamper.partner;

import java.io.*;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Michael on 2016/6/7.
 */
public class CreditInfoDigester {

    public static byte[] digest(CreditInfoInput input) {
        String principalName = input.getPrincipalName();
        String detailsChecksum = DigestUtils.sha1Hex(input.getDetails());
        String attachmentChecksum;
        try (InputStream in = new FileInputStream(input.getAttachment().getFile())) {
            attachmentChecksum = DigestUtils.sha1Hex(in);
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("附件不存在", fnfe);
        } catch (IOException ioe) {
            throw new RuntimeException("无法打开附件", ioe);
        }

        return DigestUtils.sha1(principalName + detailsChecksum + attachmentChecksum);
    }
}
