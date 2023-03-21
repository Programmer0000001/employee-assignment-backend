package mu.management.employee.service;

import java.util.List;

import mu.management.employee.entity.Employee;
import mu.management.employee.request.EmployeeListUpdateRequest;
import mu.management.employee.request.EmployeeRequestList;
import mu.management.employee.response.EmployeeResponse;

/**
 * {@link EmployeeService} defines service (business logic) methods related to the {@link mu.management.employee.service.impl.EmployeeServiceImpl} class
 *
 * @author oudayrao.ittoo
 */
public interface EmployeeService {
    List<Employee> saveMultipleEmployees(EmployeeRequestList employeeRequestList);

    List<Employee> updateEmployeeList(EmployeeListUpdateRequest employeeListUpdateRequest);

    void deleteEmployeeList(List<Long> ids);

    EmployeeResponse searchEmployeeById(Long id);

    List<EmployeeResponse> searchAllEmployees();
}
