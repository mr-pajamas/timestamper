package org.nelect.timestamper.web.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.StringUtils.trimToNull;

/**
 * <p>TODO</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public class ErrorModelBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ErrorModelBuilder.class);

    private static final Properties errorConfig;
    static {
        errorConfig = new Properties();
        try (InputStream in = ErrorModelBuilder.class.getResourceAsStream("errors.properties")) {
            errorConfig.load(in);
        } catch (IOException ioe) {
            logger.warn("Failed to load error configuration file", ioe);
        }
    }

    public static boolean validateErrorCode(String errorCode) {
        return errorCode != null && errorConfig.containsKey(errorCode);
    }

    private ErrorModel errorModel;

    public ErrorModelBuilder(String errorCode, Object... errorMessageParams) {

        errorCode = trimToNull(errorCode);
        if (errorCode == null)
            throw new IllegalArgumentException("errorCode cannot be blank");
        if (!validateErrorCode(errorCode))
            throw new IllegalArgumentException("Invalid errorCode: " + errorCode);

        errorModel = new ErrorModel();
        errorModel.setCode(errorCode);

        errorModel.setStatusCode(Integer.valueOf("4" + errorCode.substring(0, 2)));

        String messagePattern = errorConfig.getProperty(errorCode, "Invalid service request");
        errorModel.setMessage(MessageFormat.format(messagePattern, errorMessageParams));

        String parametersString = errorConfig.getProperty(errorCode + ".parameters");
        errorModel.setParametersFromString(parametersString);
    }

    public ErrorModelBuilder setErrorMessage(String errorMessage) {
        errorModel.setMessage(errorMessage);
        return this;
    }

    public ErrorModelBuilder addChildError(String errorCode, Object... errorMessageParams) {
        errorModel.addError(new ErrorModelBuilder(errorCode, errorMessageParams).build());
        return this;
    }

    public ErrorModel build() {
        return errorModel;
    }
}
