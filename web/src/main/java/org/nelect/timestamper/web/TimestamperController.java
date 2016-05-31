package org.nelect.timestamper.web;

import org.nelect.timestamper.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Michael on 2016/5/31.
 */
public abstract class TimestamperController {

    @Autowired
    protected SessionFactory sessionFactory;
}
