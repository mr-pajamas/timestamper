package org.nelect.timestamper.web;

import java.util.Properties;

import org.nelect.timestamper.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Michael on 2016/5/31.
 */
public abstract class AbstractController {

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected Properties timestamperConfig;

    @Autowired
    protected IdGenerator idGenerator;
}
