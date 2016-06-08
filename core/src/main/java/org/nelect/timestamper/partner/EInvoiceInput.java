package org.nelect.timestamper.partner;

import org.nelect.timestamper.Attachment;

/**
 * Created by Michael on 2016/6/6.
 */
public interface EInvoiceInput {

    String getCheckId();

    String getCertNumber();

    Attachment getAttachment();

    String getPrincipalName();
}
