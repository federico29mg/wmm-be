package federico29mg.wmmbe.Advices;

import federico29mg.wmmbe.Controllers.ReceiptController;
import federico29mg.wmmbe.Exceptions.ReceiptExceptions.ReceiptNotFoundException;
import federico29mg.wmmbe.Utils.Messages.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
@RestControllerAdvice(assignableTypes = {ReceiptController.class})
public class ReceiptControllerAdvice {
    @ExceptionHandler(ReceiptNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage receiptNotFoundException(ReceiptNotFoundException ex, WebRequest webRequest) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false)
        );
    }
}
