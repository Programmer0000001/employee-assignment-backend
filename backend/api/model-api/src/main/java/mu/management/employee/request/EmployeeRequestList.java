package mu.management.employee.request;

import static mu.management.employee.shared.Constant.EMPLOYEE_LIST_EMPTY;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>Wrapper class containing a list of {@link EmployeeRequest}</p>
 *
 * @author oudayrao.ittoo
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class EmployeeRequestList {

    @Valid
    @NotEmpty(message = EMPLOYEE_LIST_EMPTY)
    private List<EmployeeRequest> employeeRequestList = new ArrayList<>();
}
