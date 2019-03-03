package com.zixel.core.loggers;

import com.zixel.core.beans.Event;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CacheEventLogger extends FileEventLogger {

    // Use system property cache.size or 5 if property is not set
    @Value("${cache.size:5}")
    private int cacheSize;

    private List<Event> cache;

    public CacheEventLogger() {
    }

    public CacheEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }

    @PostConstruct
    public void initCache() {
        this.cache = new ArrayList<Event>(cacheSize);
    }

    @PreDestroy
    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        cache.stream().forEach(super::logEvent);
    }

}