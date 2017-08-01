package com.eroom.web.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    protected final Log logger = LogFactory.getLog(getClass());
}