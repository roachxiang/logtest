package com.qcd.logtest.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TacticsLogger {
    private final Logger tacticsLogger = LoggerFactory.getLogger("tactics");

    public void log(String msg){
        tacticsLogger.info(msg);
    }
}
