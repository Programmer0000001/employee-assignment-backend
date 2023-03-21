package mu.management.employee.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Custom general exception class</p>
 *
 * @author oudayrao.ittoo
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeManagementException extends RuntimeException {
    private final EmployeeManagementMsgKey employeeManagementMsgKey;
    private final HttpStatus statusCode;
    private final String[] params;

    public EmployeeManagementException(EmployeeManagementMsgKey employeeManagementMsgKey, HttpStatus httpStatus, String... params) {
        this.employeeManagementMsgKey = employeeManagementMsgKey;
        this.statusCode = httpStatus;
        this.params = params;
    }

    public EmployeeManagementException(EmployeeManagementMsgKey employeeManagementMsgKey, HttpStatus httpStatus) {
        this.employeeManagementMsgKey = employeeManagementMsgKey;
        this.params = null;
        this.statusCode = httpStatus;
    }
}
