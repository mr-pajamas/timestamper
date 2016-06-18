package org.nelect.timestamper.web.service;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static org.apache.commons.lang3.StringUtils.trimToNull;

/**
 * <p>TODO</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorModel {

    private int statusCode;

    private String code;
    private String message;
    private Set<String> parameters = new HashSet<>();

    private Collection<ErrorModel> errors = new LinkedList<>();

    @JsonIgnore
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getParameters() {
        return parameters;
    }

    public void setParameters(Set<String> parameters) {
        this.parameters = parameters;
    }

    public Collection<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(Collection<ErrorModel> errors) {
        this.errors = errors;
    }

    public void setParametersFromString(String parametersString) {
        parametersString = trimToEmpty(parametersString);
        String[] parameterStrings = parametersString.split("\\s*,\\s*");

        parameters.clear();

        for (String parameter : parameterStrings) {
            parameter = trimToNull(parameter);
            if (parameter != null) parameters.add(parameter);
        }
    }

    public void addError(ErrorModel error) {
        errors.add(error);
    }
}
