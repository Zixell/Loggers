package com.zixel.core.spring;


import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.zixel.core.beans.EventType;
import com.zixel.core.loggers.EventLogger;

@Configuration
public class LoggerConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Qualifier("loggers.consoleEventLogger")
    private EventLogger consoleEventLogger;

    @Qualifier("loggers.fileEventLogger")
    private EventLogger fileEventLogger;

    @Qualifier("loggers.combinedEventLogger")
    private EventLogger combinedEventLogger;

    @Qualifier("loggers.cacheEventLogger")
    private EventLogger cacheEventLogger;

    @Bean
    public Collection<EventLogger> combinedLoggers() {
        Collection<EventLogger> loggers = new ArrayList<EventLogger>(2);
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        return loggers;
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map<EventType, EventLogger> map = new EnumMap<>(EventType.class);
        map.put(EventType.INFO, consoleEventLogger);
        map.put(EventType.ERROR, combinedEventLogger);
        return map;
    }

    @Bean
    public EventLogger defaultLogger() {
        return cacheEventLogger;
    }

}
