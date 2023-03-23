package mu.management.employee.request;

import static mu.management.employee.shared.Constant.ID_NULL;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>Class containing request sent to update one Employee at a time</p>
 *
 * @author oudayrao.ittoo
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class EmployeeUpdateRequest extends EmployeeRequest {

    @NotNull(message = ID_NULL)
    private Long userId;
}
