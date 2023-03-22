package mu.management.employee.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mu.management.employee.translation.Translator;

/**
 * <p>A class that stores the error message</p>
 *
 * @author oudayrao.ittoo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private String errorMessage;
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private HttpStatus httpStatus;

    public ExceptionResponse(HttpStatus httpStatus, Object[] errorKeys) {
        this.httpStatus = httpStatus;
        this.errorMessage = Arrays.stream(errorKeys).map((errorKey) -> Translator.toLocale(String.valueOf(errorKey))).collect(Collectors.joining(","));
    }

    public ExceptionResponse(HttpStatus httpStatus, String errorKey, Object[] params) {
        this.httpStatus = httpStatus;
        this.errorMessage = Translator.toLocale(errorKey, params);
    }

    public ExceptionResponse(HttpStatus httpStatus, String errorKey) {
        this.httpStatus = httpStatus;
        this.errorMessage = Translator.toLocale(errorKey);
    }
}


