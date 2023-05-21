package se.kth.iv1350.processSale.exception;

/**
 * This class represents a custom exception that is thrown when an item does not exist in the inventory system.
 * The class extends from Java's built-in Exception class, which allows it to behave like any other exception, but with additional properties.
 * Specifically, this class includes an error code and an error message.
 */
public class ItemDoesNotExistException extends Exception {

    private int errorCode;
    private String errorMessage;

    /**
     * Constructs a new ItemDoesNotExistException with specified detail message and error code.
     *
     * @param errorCode The integer error code.
     * @param errorMessage The detailed error message.
     */
    public ItemDoesNotExistException(int errorCode, String errorMessage) {
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
        return "ItemDoesNotExistException{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}

