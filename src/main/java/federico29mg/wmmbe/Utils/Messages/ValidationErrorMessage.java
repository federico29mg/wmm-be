package federico29mg.wmmbe.Utils.Messages;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ValidationErrorMessage extends ErrorMessage {
    private final Map<String, String> errors;
    public ValidationErrorMessage(int statusCode, LocalDateTime timestamp, String message, String description, Map<String, String> errors) {
        super(statusCode, timestamp, message, description);
        this.errors = errors;
    }
}
