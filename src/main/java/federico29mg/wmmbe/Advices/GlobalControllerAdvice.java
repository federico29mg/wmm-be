package federico29mg.wmmbe.Advices;

import federico29mg.wmmbe.Utils.Messages.ValidationErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorMessage methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest) {
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        });

        return new ValidationErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Field(s) with invalid data",
                webRequest.getDescription(false),
                validationErrors
        );
    }
}
