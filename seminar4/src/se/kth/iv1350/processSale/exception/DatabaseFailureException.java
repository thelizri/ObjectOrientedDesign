package se.kth.iv1350.processSale.exception;

/**
 * This class represents a custom exception that is thrown when the application is unable to establish a connection with the database,
 * indicating that the database is not running. The class extends from Java's built-in Exception class, providing additional properties
 * such as an error code and an error message.
 */
public class DatabaseFailureException extends Exception {

    private final int errorCode;
    private final String errorMessage;

    /**
     * Constructs a new DatabaseFailureException with specified detail message and error code.
     *
     * @param errorCode    The integer error code associated with the exception.
     * @param errorMessage The detailed error message providing information about the exception.
     */
    public DatabaseFailureException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Retrieves the error code associated with the exception.
     *
     * @return The integer error code.
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Retrieves the detailed error message associated with the exception.
     *
     * @return The error message string.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns a string representation of the custom exception.
     *
     * @return A string representation of the exception, including the error code and error message.
     */
    @Override
    public String toString() {
        return "DatabaseOfflineException{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
