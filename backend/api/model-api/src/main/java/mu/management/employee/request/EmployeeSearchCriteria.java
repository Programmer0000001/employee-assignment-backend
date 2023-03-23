package mu.management.employee.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Request sent to search for employee using query dsl</p>
 *
 * @author oudayrao.ittoo
 */
@Data
@NoArgsConstructor
public class EmployeeSearchCriteria {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String buCode;
    private String buName;
    private String regionCode;
    private String regionName;
    private @NotBlank String sortOrder;
    private @NotBlank String sortBy;
    private @NotNull Integer pageSize;
    private @NotNull Integer pageNumber;
}
