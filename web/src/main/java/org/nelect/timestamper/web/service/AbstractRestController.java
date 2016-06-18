package org.nelect.timestamper.web.service;

import org.nelect.timestamper.web.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Michael on 2016/6/9.
 */
public abstract class AbstractRestController extends AbstractController {

    @Autowired
    protected RestControllerAdvice controllerAdvice;
}
