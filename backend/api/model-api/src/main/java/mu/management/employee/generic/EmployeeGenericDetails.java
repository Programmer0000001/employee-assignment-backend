package mu.management.employee.generic;

import static mu.management.employee.shared.Constant.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>Class containing common field to Employee related requests</p>
 *
 * @author oudayrao.ittoo
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class EmployeeGenericDetails {
    @NotBlank(message = EMPLOYEE_FIRST_NAME_EMPTY)
    @Size(max = 30, message = EMPLOYEE_FIRST_NAME_SIZE)
    private String firstName;

    @NotBlank(message = EMPLOYEE_LAST_NAME_EMPTY)
    @Size(max = 30, message = EMPLOYEE_LAST_NAME_SIZE)
    private String lastName;

    @NotBlank(message = BU_CODE_EMPTY)
    @Size(max = 20, message = BU_CODE_SIZE)
    private String buCode;

    @NotBlank(message = BU_NAME_EMPTY)
    @Size(max = 20, message = BU_NAME_SIZE)
    private String buName;

    @NotBlank(message = REGION_CODE_EMPTY)
    @Size(max = 20, message = REGION_CODE_SIZE)
    private String regionCode;

    @NotBlank(message = REGION_NAME_EMPTY)
    @Size(max = 20, message = REGION_NAME_SIZE)
    private String regionName;
}
