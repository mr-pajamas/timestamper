package org.nelect.timestamper;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Michael on 2016/7/3.
 */
public class CertificateDigester {

    public static byte[] digest(String principalName, CertificateInput input) {
        //String principalName = input.getPrincipal().getName();
        String title = input.getTitle();
        String attachmentChecksum = input.getAttachmentId();

/*
        try (InputStream in = new FileInputStream(input.getAttachment().getFile())) {
            attachmentChecksum = DigestUtils.sha1Hex(in);
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("附件不存在", fnfe);
        } catch (IOException ioe) {
            throw new RuntimeException("无法打开附件", ioe);
        }
*/

        return DigestUtils.sha1(principalName + ":" + title + ":" + attachmentChecksum);
    }
}
