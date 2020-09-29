package com.api.enrolment.student.exceptions;

public class EnrolmentException extends Exception {

    private String errorCode;
    private String errorMessage;

    public EnrolmentException(Throwable throwable) {
        super(throwable);
    }

    public EnrolmentException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public EnrolmentException(String msg) {
        super(msg);
    }

    /**
     * @param message
     * @param errorCode
     */
    public EnrolmentException(String message, String errorCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorCode + " : " + this.getErrorMessage();
    }
}
