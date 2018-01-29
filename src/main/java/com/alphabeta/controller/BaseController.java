package com.alphabeta.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


public abstract class BaseController {
    /* logger */
    protected Logger logger = LoggerFactory.getLogger(getClass());

}
