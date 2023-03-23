package mu.management.employee.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * <p>Response received when a query for employee is done using query dsl</p>
 *
 * @author oudayrao.ittoo
 */
@Data
@Builder
public class EmployeeListResponse {
    private Long totalElement;
    List<EmployeeResponse> employeeResponses;
}
