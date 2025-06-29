package dev.danielsebastian.postmanager.exception;

public class UsernameOrPasswordInvalidException extends RuntimeException {
    public UsernameOrPasswordInvalidException(String string) {
        super(string);
    }
}
