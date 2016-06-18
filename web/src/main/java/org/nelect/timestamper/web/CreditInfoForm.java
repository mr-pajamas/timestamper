package org.nelect.timestamper.web;

/**
 * Created by Michael on 2016/6/6.
 */
public class CreditInfoForm {

    public static enum Type {
        MANUFACTURER, PRODUCT
    }

    private Type type;
    private String checkId;
    private String name;
    private String details;

    private String principalName;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }
}
