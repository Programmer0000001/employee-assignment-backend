package mu.management.employee.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <p>Class containing error key</p>
 *
 * @author oudayrao.ittoo
 */
@RequiredArgsConstructor
@Getter
public enum EmployeeManagementMsgKey {
    GENERAL("general.error"),
    EMPLOYEE_NOT_FOUND("employee.not.found");
    private final String msgKey;
}
