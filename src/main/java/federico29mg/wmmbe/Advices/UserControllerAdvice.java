package federico29mg.wmmbe.Advices;

import federico29mg.wmmbe.Controllers.UserController;
import federico29mg.wmmbe.Exceptions.UserExceptions.EmailAlreadyExistsException;
import federico29mg.wmmbe.Exceptions.UserExceptions.UserNotFoundException;
import federico29mg.wmmbe.Exceptions.UserExceptions.UsernameAlreadyExistsException;
import federico29mg.wmmbe.Utils.Messages.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice(assignableTypes = {UserController.class})
public class UserControllerAdvice {
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage usernameAlreadyExistsException(UsernameAlreadyExistsException ex, WebRequest webRequest) {
        return new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false)
        );
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage emailAlreadyExistsException(EmailAlreadyExistsException ex, WebRequest webRequest) {
        return new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false)
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage userNotFoundException(UserNotFoundException ex, WebRequest webRequest) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false)
        );
    }
}
