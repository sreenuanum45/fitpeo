package exceptions;

public class AuthenticationFailedException extends BaseException{
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
