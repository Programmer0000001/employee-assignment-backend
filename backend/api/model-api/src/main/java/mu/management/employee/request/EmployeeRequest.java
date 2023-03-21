package mu.management.employee.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import mu.management.employee.generic.EmployeeGenericDetails;

/**
 * <p>Request to save a single Employee</p>
 *
 * @author oudayrao.ittoo
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class EmployeeRequest extends EmployeeGenericDetails {
}
