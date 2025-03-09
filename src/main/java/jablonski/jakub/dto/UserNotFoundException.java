package jablonski.jakub.dto;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return 404;
    }
}
