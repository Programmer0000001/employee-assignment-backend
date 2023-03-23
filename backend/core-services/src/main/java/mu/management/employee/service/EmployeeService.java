package mu.management.employee.service;

import java.util.List;

import mu.management.employee.entity.Employee;
import mu.management.employee.request.EmployeeListUpdateRequest;
import mu.management.employee.request.EmployeeRequestList;
import mu.management.employee.request.EmployeeSearchCriteria;
import mu.management.employee.response.EmployeeListResponse;
import mu.management.employee.response.EmployeeResponse;

/**
 * {@link EmployeeService} defines service (business logic) methods related to the {@link mu.management.employee.service.impl.EmployeeServiceImpl} class
 *
 * @author oudayrao.ittoo
 */
public interface EmployeeService {

    /**
     * <p>Method to save a list of employee</p>
     *
     * @param employeeRequestList {@link EmployeeRequestList}
     * @return List of {@link Employee}
     */
    List<Employee> saveMultipleEmployees(EmployeeRequestList employeeRequestList);


    /**
     * <p>Method to update a list of employee</p>
     *
     * @param employeeListUpdateRequest {@link EmployeeListUpdateRequest}
     * @return List of {@link Employee}
     */
    List<Employee> updateEmployeeList(EmployeeListUpdateRequest employeeListUpdateRequest);

    /**
     * <P>Method to delete list of employee</P>
     *
     * @param ids List of id to delete
     */
    void deleteEmployeeList(List<Long> ids);


    /**
     * <p>Method to search an employee given their id (identifier)</p>
     *
     * @param id Identifier of employee
     * @return {@link EmployeeResponse}
     */
    EmployeeResponse searchEmployeeById(Long id);


    /**
     * <p>Method to find all employees</p>
     *
     * @return List of {@link EmployeeResponse}
     */
    List<EmployeeResponse> searchAllEmployees();


    /**
     * <p>Method to search employee using filtering given {@link EmployeeSearchCriteria}</p>
     *
     * @param employeeSearchCriteria {@link EmployeeSearchCriteria}
     * @return {@link EmployeeListResponse}
     */
    EmployeeListResponse searchEmployeeByFilter(EmployeeSearchCriteria employeeSearchCriteria);
}
