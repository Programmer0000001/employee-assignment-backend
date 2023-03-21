package mu.management.employee.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import mu.management.employee.generic.EmployeeGenericDetails;

/**
 * <p>Response received when a query for an Employee is done</p>
 *
 * @author oudayrao.ittoo
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class EmployeeResponse extends EmployeeGenericDetails {

    private Long userId;
}
