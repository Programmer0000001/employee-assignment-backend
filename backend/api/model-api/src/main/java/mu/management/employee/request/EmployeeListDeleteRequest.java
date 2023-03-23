package mu.management.employee.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>Wrapper class containing Ids of employees to delete</p>
 *
 * @author oudayrao.ittoo
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class EmployeeListDeleteRequest {
    @NotEmpty(message = "employee.list.delete.mandatory")
    private List<Long> ids;
}
