package mu.management.employee.request;

import static mu.management.employee.shared.Constant.EMPLOYEE_LIST_EMPTY;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>Class containing request sent to update a list of Employees</p>
 *
 * @author oudayrao.ittoo
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class EmployeeListUpdateRequest {

    @Valid
    @NotEmpty(message = EMPLOYEE_LIST_EMPTY)
    private List<EmployeeUpdateRequest> employeeUpdateRequests = new ArrayList<>();
}
