package com.zixel.core.loggers;


import java.util.Collection;

import com.zixel.core.beans.Event;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;


@Component
public class CombinedEventLogger implements EventLogger {

    @Resource(name = "combinedLoggers")
    private Collection<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        for (EventLogger eventLogger : loggers) {
            eventLogger.logEvent(event);
        }
    }

}