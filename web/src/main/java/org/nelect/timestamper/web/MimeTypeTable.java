package org.nelect.timestamper.web;

import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

/**
 * Created by Michael on 2016/7/5.
 */
public class MimeTypeTable {

    private static final MimeTypes MIME_TYPES = MimeTypes.getDefaultMimeTypes();

    public static String getExtension(String mimeType) {
        try {
            return MIME_TYPES.forName(mimeType).getExtension();
        } catch (MimeTypeException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
