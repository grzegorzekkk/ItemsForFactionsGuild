package pl.stillcraft.grzegorzekkk.itemsforfactionsguild.utils;

import java.util.logging.Logger;

public class ConsoleLogger {

    /**
     * Stores logger for this plugin
     */
    private static Logger log;

    private ConsoleLogger() {
    }

    /**
     * Set reference to logger of this plugin
     *
     * @param logger this plugin logger
     */
    public static void setLogger(Logger logger) {
        log = logger;
    }

    /**
     * Send info log message to console
     *
     * @param msg Message to send as info in console
     */
    public static void info(String msg) {
        log.info(msg);
    }

    /**
     * Send warning log message to console
     *
     * @param msg Message to send as warning in console
     */
    public static void warn(String msg) {
        log.warning(msg);
    }

    /**
     * Send debug log message to console
     *
     * @param msg Message to send as debug in console
     */
    public static void debug(String msg) {
        log.fine(msg);
    }

    /**
     * Send severe log message to console
     *
     * @param msg Message to send as severe in console
     */
    public static void severe(String msg) {
        log.severe(msg);
    }
}
