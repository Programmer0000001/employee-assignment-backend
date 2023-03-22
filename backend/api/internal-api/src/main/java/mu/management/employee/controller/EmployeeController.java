package mu.management.employee.controller;

import static mu.management.employee.shared.Constant.EMPLOYEE_LIST_ID;
import static mu.management.employee.shared.Constant.ID_NULL;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.management.employee.entity.Employee;
import mu.management.employee.request.EmployeeListUpdateRequest;
import mu.management.employee.request.EmployeeRequestList;
import mu.management.employee.request.EmployeeUpdateRequest;
import mu.management.employee.response.EmployeeResponse;
import mu.management.employee.service.EmployeeService;

/**
 * <p>Controller class to handler Employee related API requests</p>
 *
 * @author oudayrao.ittoo
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/employee")
@Validated
@Slf4j
@CrossOrigin(origins = "*") //For front end to make api call to backend
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * <p>Method to save a list of {@link EmployeeRequestList}</p>
     *
     * @param employeeRequestList {@link EmployeeRequestList}
     * @return list of {@link EmployeeResponse}
     */
    @PostMapping
    public List<Employee> saveMultipleEmployees(@Valid @RequestBody EmployeeRequestList employeeRequestList) {
        log.info("Saving list of employees given employeeRequestList {}", employeeRequestList);
        return employeeService.saveMultipleEmployees(employeeRequestList);
    }

    /**
     * <p>Method to update a list of employees given {@link  EmployeeListUpdateRequest}</p>
     *
     * @param employeeListUpdateRequest {@link EmployeeListUpdateRequest}
     */
    @PutMapping
    public List<Employee> updateEmployeeList(@Valid @RequestBody EmployeeListUpdateRequest employeeListUpdateRequest) {
        log.info("Updating list of employees with ids {}", employeeListUpdateRequest.getEmployeeUpdateRequests().stream().map(EmployeeUpdateRequest::getUserId).toList());
        return employeeService.updateEmployeeList(employeeListUpdateRequest);
    }


    /**
     * <p>Method to delete list of employees given their ids</p>
     *
     * @param ids Ids to delete
     */
    @DeleteMapping
    public void deleteEmployeeList(@RequestParam @NotEmpty(message = EMPLOYEE_LIST_ID) List<Long> ids) {
        employeeService.deleteEmployeeList(ids);
        log.info("List of employees with ids {}, DELETED", ids);
    }

    /**
     * <p>API to search for Employee given its id</p>
     *
     * @param id Identifier of employee
     * @return {@link EmployeeResponse}
     */
    @GetMapping
    public EmployeeResponse searchEmployeeById(@RequestParam @NotNull(message = ID_NULL) Long id) {
        log.debug("Inside Search Employee by Id API");
        return employeeService.searchEmployeeById(id);
    }

    /**
     * <p>API to find all employees</p>
     *
     * @return {@link EmployeeResponse}
     */
    @GetMapping("/all")
    public List<EmployeeResponse> searchAllEmployees() {
        log.debug("Inside Search All Employees API");
        return employeeService.searchAllEmployees();
    }
}
