package mu.management.employee.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import mu.management.employee.entity.Employee;
import mu.management.employee.request.EmployeeRequest;
import mu.management.employee.request.EmployeeUpdateRequest;
import mu.management.employee.response.EmployeeResponse;

/**
 * <p>Mapper class for Employee</p>
 *
 * @author oudayrao.ittoo
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeRequest employeeRequest);

    void toEmployee(@MappingTarget Employee employee, EmployeeRequest employeeRequest);

    EmployeeResponse toEmployeeResponse(Employee employee);

    List<EmployeeResponse> toEmployeeResponse(List<Employee> employee);

    Employee toEmployee(EmployeeUpdateRequest employeeUpdateRequest);
}
