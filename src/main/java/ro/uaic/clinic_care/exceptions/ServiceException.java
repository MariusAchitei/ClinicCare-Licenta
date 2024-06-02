package ro.uaic.clinic_care.exceptions;

public class ServiceException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public ServiceException(String message) {
        super(message);
    }
}
