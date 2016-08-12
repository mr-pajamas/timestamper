package org.nelect.timestamper.web;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Michael on 2016/8/12.
 */
@ControllerAdvice(basePackages = "org.nelect.timestamper.web")
public class ConfigControllerAdvice {

    private final Properties timestamperConfig;

    @Autowired
    public ConfigControllerAdvice(Properties timestamperConfig) {
        this.timestamperConfig = timestamperConfig;
    }

    @ModelAttribute("explorerEndpoint")
    public String transactionExplorerEndpoint() {
        return timestamperConfig.getProperty("timestamp.explorer.endpoint");
    }
}
