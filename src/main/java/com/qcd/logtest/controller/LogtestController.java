package com.qcd.logtest.controller;

import com.qcd.logtest.logger.TacticsLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogtestController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private TacticsLogger tacticsLogger;

    @GetMapping(value = "/test")
    public String test(){
        LOGGER.debug("debug log");
        LOGGER.info("info log");
        LOGGER.error("error log");

        tacticsLogger.log("aaaa");

        return "ok";
    }
}
