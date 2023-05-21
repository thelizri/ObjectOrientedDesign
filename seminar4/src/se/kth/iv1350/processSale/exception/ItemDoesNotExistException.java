package se.kth.iv1350.processSale.exception;

public class ItemDoesNotExistException extends Exception {

    private int errorCode;
    private String errorMessage;

    public ItemDoesNotExistException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ItemDoesNotExistException{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}

