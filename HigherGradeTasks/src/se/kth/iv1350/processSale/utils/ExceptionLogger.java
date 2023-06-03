package se.kth.iv1350.processSale.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * The {@code ExceptionLogger} class provides a simple utility for logging exceptions
 * to a file named "exceptions.log" in the current working directory.
 * It uses the {@code java.util.logging} package for logging purposes.
 * <p>
 * This class is designed to be used as a static utility and cannot be instantiated.
 * </p>
 */
public class ExceptionLogger {

    private static final Logger logger = Logger.getLogger(ExceptionLogger.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("exceptions.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to initialize ExceptionLogger", e);
        }
    }

    private ExceptionLogger() {
        // Private constructor to prevent instantiation
    }

    /**
     * Logs an exception with the specified logging level and prints a message to the user.
     * <p>
     * Logging levels include:
     * <ul>
     *   <li>SEVERE (highest)</li>
     *   <li>WARNING</li>
     *   <li>INFO</li>
     *   <li>CONFIG</li>
     *   <li>FINE</li>
     *   <li>FINER</li>
     *   <li>FINEST</li>
     * </ul>
     * </p>
     *
     * @param exception     the {@code Throwable} to be logged
     * @param level         the {@code Level} at which the exception should be logged
     * @param messageToUser a {@code String} message to be displayed to the user
     */
    public static void logException(Throwable exception, Level level, String messageToUser) {
        System.out.println(messageToUser);
        logger.log(level, exception.getMessage(), exception);
    }
}
