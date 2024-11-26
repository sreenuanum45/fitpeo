package exceptions;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(String resourceName, String resourceId) {
        super(String.format("%s with ID '%s' was not found.", resourceName, resourceId));
    }
}
