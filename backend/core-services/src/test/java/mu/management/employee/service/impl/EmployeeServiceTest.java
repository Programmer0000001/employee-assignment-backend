package mu.management.employee.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import mu.management.employee.config.MockAbstractTest;
import mu.management.employee.entity.Employee;
import mu.management.employee.exception.EmployeeManagementException;
import mu.management.employee.exception.EmployeeManagementMsgKey;
import mu.management.employee.mapper.EmployeeMapper;
import mu.management.employee.repository.EmployeeRepository;
import mu.management.employee.request.EmployeeListUpdateRequest;
import mu.management.employee.request.EmployeeRequest;
import mu.management.employee.request.EmployeeRequestList;
import mu.management.employee.request.EmployeeUpdateRequest;
import mu.management.employee.response.EmployeeResponse;
import mu.management.employee.service.EmployeeService;
import mu.management.employee.utils.EmployeeTestDataUtil;

/**
 * <p>Unit test for {@link EmployeeService}</p>
 *
 * @author oudayrao.ittoo
 */
class EmployeeServiceTest extends MockAbstractTest {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    EmployeeRepository employeeRepository;
    EmployeeRequestList employeeRequestList;
    List<Employee> employeeListActualSaved;

    @BeforeEach
    public void setup() {
        //Request to save
        employeeRequestList = EmployeeTestDataUtil.buildEmployeeRequestList();

        //Saving data
        employeeListActualSaved = employeeService.saveMultipleEmployees(employeeRequestList);
    }

    @Test
    @DisplayName("[saveMultipleEmployees] Should save a list of employees")
    void test_saveMultipleEmployees() {
        //Check result after save
        assertNotNull(employeeListActualSaved);

        EmployeeRequest employeeRequestExpected = EmployeeTestDataUtil.buildEmployeeRequestList().getEmployeeRequestList().get(0);
        Employee employeeActual = employeeListActualSaved.get(0);
        Employee employeeExpected = employeeMapper.toEmployee(employeeRequestExpected);

        assertEquals(employeeExpected, employeeActual, "Employee is not correctly saved!!");
    }

    @Test
    @DisplayName("[searchAllEmployees] Should return all employees")
    void test_searchAllEmployees() {
        List<EmployeeResponse> employeeResponses = employeeService.searchAllEmployees();

        assertNotNull(employeeResponses);
        assertEquals(1, employeeResponses.size());
    }

    /**
     * <p>Test data used for test_searchEmployeeById and test_deleteEmployeeList functions</p>
     *
     * @return Stream Argument
     */
    static Stream<Arguments> testData_employeeWithById() {

        return Stream.of(
                arguments(true, null, null, null),
                arguments(false, "9999999999", EmployeeManagementMsgKey.EMPLOYEE_NOT_FOUND, HttpStatus.NOT_FOUND)
        );
    }

    @ParameterizedTest
    @MethodSource("testData_employeeWithById")
    @DisplayName("[searchEmployeeById] Should search the employee")
    void test_searchEmployeeById(Boolean valid, Long id, EmployeeManagementMsgKey errorMessage, HttpStatus errorStatusCode) {

        if (valid) {
            Long userId = employeeListActualSaved.get(0).getUserId(); //get user id saved
            EmployeeResponse employeeResponse = employeeService.searchEmployeeById(userId); //search the user
            assertNotNull(employeeResponse);
        } else {
            EmployeeManagementException employeeManagementException = assertThrows(EmployeeManagementException.class, () -> employeeService.searchEmployeeById(id));

            assertEquals(errorMessage, employeeManagementException.getEmployeeManagementMsgKey());
            assertEquals(errorStatusCode, employeeManagementException.getStatusCode());
        }
    }


    @ParameterizedTest
    @MethodSource("testData_employeeWithById")
    @DisplayName("[deleteEmployeeList] Should delete the employee")
    void test_deleteEmployeeList(Boolean valid, Long id, EmployeeManagementMsgKey errorMessage, HttpStatus errorStatusCode) {

        if (valid) {
            Long userId = employeeListActualSaved.get(0).getUserId(); //get user id saved
            employeeService.deleteEmployeeList(List.of(userId)); //delete the user

            Assertions.assertTrue(employeeRepository.findById(userId).isEmpty()); //search the user deleted -- should return empty
        } else {
            EmployeeManagementException employeeManagementException = assertThrows(EmployeeManagementException.class, () -> employeeService.deleteEmployeeList(List.of(id)));

            assertEquals(errorMessage, employeeManagementException.getEmployeeManagementMsgKey());
            assertEquals(errorStatusCode, employeeManagementException.getStatusCode());
        }
    }

    /**
     * <p>Test data used for test_updateEmployeeList function</p>
     *
     * @return Stream Argument
     */
    static Stream<Arguments> testData_updateEmployeeList() {

        EmployeeListUpdateRequest employeeListUpdateRequestCorrect = EmployeeTestDataUtil.buildEmployeeListUpdateRequest();
        EmployeeListUpdateRequest employeeListUpdateRequestNotFound = EmployeeTestDataUtil.buildEmployeeListUpdateRequest();
        employeeListUpdateRequestNotFound.getEmployeeUpdateRequests().get(0).setUserId(99999L);

        return Stream.of(
                arguments(true, employeeListUpdateRequestCorrect, null, null),
                arguments(false, employeeListUpdateRequestNotFound, EmployeeManagementMsgKey.EMPLOYEE_NOT_FOUND, HttpStatus.NOT_FOUND)
        );
    }


    @ParameterizedTest
    @MethodSource("testData_updateEmployeeList")
    @DisplayName("[updateEmployeeList] Should update the employee")
    void test_updateEmployeeList(Boolean valid, EmployeeListUpdateRequest employeeListUpdateRequest, EmployeeManagementMsgKey errorMessage, HttpStatus errorStatusCode) {

        if (valid) {
            Long userId = employeeListActualSaved.get(0).getUserId(); //search saved employee
            employeeListUpdateRequest.getEmployeeUpdateRequests().get(0).setUserId(userId); //set the id of update request
            List<Employee> employeeList = employeeService.updateEmployeeList(employeeListUpdateRequest); //update the employee
            assertNotNull(employeeList);

            EmployeeUpdateRequest employeeUpdateRequestExpected = employeeListUpdateRequest.getEmployeeUpdateRequests().get(0); //get update request
            Employee employeeActual = employeeList.get(0); //get actual result after update
            Employee employeeExpected = employeeMapper.toEmployee(employeeUpdateRequestExpected); //map

            assertEquals(employeeExpected, employeeActual); //compare
        } else {
            EmployeeManagementException employeeManagementException = assertThrows(EmployeeManagementException.class, () -> employeeService.updateEmployeeList(employeeListUpdateRequest));

            assertEquals(errorMessage, employeeManagementException.getEmployeeManagementMsgKey());
            assertEquals(errorStatusCode, employeeManagementException.getStatusCode());
        }
    }

}