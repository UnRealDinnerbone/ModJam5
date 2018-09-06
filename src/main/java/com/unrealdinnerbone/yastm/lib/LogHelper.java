package com.unrealdinnerbone.yaum.lib;

import com.unrealdinnerbone.yaum.lib.util.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;


public class LogHelper {

    private final Logger logger;

    public LogHelper(Logger logger) {
        this.logger = logger;
    }

    public void log(Level logLevel, String string) {
        logger.log(logLevel, string);
    }


    public void log(Level logLevel, Object object) {
        log(logLevel, String.valueOf(object));
    }


    public void all(Object object, Object... replacements) {
        log(Level.ALL, StringUtils.format(object, replacements));
    }

    public void debug(Object object, Object... replacements) {
        log(Level.DEBUG, StringUtils.format(object, replacements));
    }

    public void error(Object object, Object... replacements) {
        log(Level.ERROR, StringUtils.format(object, replacements));
    }

    public void fatal(Object object, Object... replacements) {
        log(Level.FATAL, StringUtils.format(object, replacements));
    }

    public void info(Object object, Object... replacements) {
        log(Level.INFO, StringUtils.format(object, replacements));
    }

    public void off(Object object, Object... replacements) {
        log(Level.OFF, StringUtils.format(object, replacements));
    }

    public void trace(Object object, Object... replacements) {
        log(Level.TRACE, StringUtils.format(object, replacements));
    }

    public void warn(Object object) {
        log(Level.WARN, object);
    }
}