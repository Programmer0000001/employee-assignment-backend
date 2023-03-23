package mu.management.employee.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 *<p>Global exception handler</p>
 *
 * @author oudayrao.ittoo
 */
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * <p>Intercept exceptions relating to constrain violation</p>
     *
     * @param ex {@link ConstraintViolationException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);

        Object[] obj = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toArray();

        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, obj);
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), exceptionResponse.getHttpStatus());
    }

    /**
     * <p>Handle specific exceptions for this project</p>
     *
     * @param ex {@link EmployeeManagementException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(EmployeeManagementException.class)
    public ResponseEntity<Object> handleEmployeeManagementException(EmployeeManagementException ex) {
        log.error(ex.getMessage(), ex);

        //Get exception key
        EmployeeManagementMsgKey employeeManagementMsgKey = ex.getEmployeeManagementMsgKey();

        //Get exception status code
        HttpStatus statusCode = ex.getStatusCode();

        //Get exception parameters
        String[] params = ex.getParams();

        //If no key is found
        if (null == employeeManagementMsgKey) {
            employeeManagementMsgKey = EmployeeManagementMsgKey.GENERAL;
        }

        //If no status is found
        if (null == statusCode) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ExceptionResponse exceptionResponse;

        //If no parameter is found
        if (null != params) {
            exceptionResponse = new ExceptionResponse(statusCode, employeeManagementMsgKey.getMsgKey(), params);
        } else {
            exceptionResponse = new ExceptionResponse(statusCode, employeeManagementMsgKey.getMsgKey());
        }

        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), exceptionResponse.getHttpStatus());
    }

    /**
     * <p>Jakarta bean validation exception</p>
     *
     * @param ex {@link MethodArgumentNotValidException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);

        Object[] obj = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toArray();

        //Get all errors and sent it to the ExceptionResponse overloaded constructor
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, obj);

        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), exceptionResponse.getHttpStatus());
    }

    /**
     * <p>Exception thrown for general issues, for cases not catered for for instance database down</p>
     *
     * @param ex {@link Exception}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        log.error(ex.getMessage(), ex);

        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, EmployeeManagementMsgKey.GENERAL.getMsgKey());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), exceptionResponse.getHttpStatus());
    }
}
