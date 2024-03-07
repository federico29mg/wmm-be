package federico29mg.wmmbe.Exceptions.UserExceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String errorMessage) { super(errorMessage); }
}
