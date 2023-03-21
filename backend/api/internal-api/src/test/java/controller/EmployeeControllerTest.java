package controller;

import static mu.management.employee.shared.Constant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;

import config.RestControllerMockAbstractTest;
import lombok.SneakyThrows;
import mu.management.employee.request.EmployeeListUpdateRequest;
import mu.management.employee.request.EmployeeRequestList;
import util.ControllerTestDataUtils;

/**
 * <p>Unit test for {@link mu.management.employee.controller.EmployeeController}</p>
 *
 * @author oudayrao.ittoo
 */
public class EmployeeControllerTest extends RestControllerMockAbstractTest {

    static Stream<Arguments> testData_saveMultipleEmployees() {

        //Correct Request
        EmployeeRequestList employeeRequestListCorrect = ControllerTestDataUtils.buildEmployeeRequestList();

        //Incorrect Request --setting all attributes to null
        EmployeeRequestList employeeRequestListIncorrect = ControllerTestDataUtils.buildEmployeeRequestList();
        employeeRequestListIncorrect.getEmployeeRequestList().get(0).setFirstName(null);
        employeeRequestListIncorrect.getEmployeeRequestList().get(0).setLastName(null);
        employeeRequestListIncorrect.getEmployeeRequestList().get(0).setBuCode(null);
        employeeRequestListIncorrect.getEmployeeRequestList().get(0).setBuName(null);
        employeeRequestListIncorrect.getEmployeeRequestList().get(0).setRegionCode(null);
        employeeRequestListIncorrect.getEmployeeRequestList().get(0).setRegionName(null);

        //Incorrect request --Setting all attributes data above max length
        String randomAlphabetic = RandomStringUtils.randomAlphabetic(31);
        EmployeeRequestList employeeRequestListIncorrectSize = ControllerTestDataUtils.buildEmployeeRequestList();
        employeeRequestListIncorrectSize.getEmployeeRequestList().get(0).setFirstName(randomAlphabetic);
        employeeRequestListIncorrectSize.getEmployeeRequestList().get(0).setLastName(randomAlphabetic);
        employeeRequestListIncorrectSize.getEmployeeRequestList().get(0).setBuCode(randomAlphabetic);
        employeeRequestListIncorrectSize.getEmployeeRequestList().get(0).setBuName(randomAlphabetic);
        employeeRequestListIncorrectSize.getEmployeeRequestList().get(0).setRegionCode(randomAlphabetic);
        employeeRequestListIncorrectSize.getEmployeeRequestList().get(0).setRegionName(randomAlphabetic);

        return Stream.of(
                arguments(false, employeeRequestListCorrect, status().isOk(), null),
                arguments(true, EmployeeRequestList.builder().employeeRequestList(null).build(), status().isBadRequest(), Set.of(EMPLOYEE_LIST_EMPTY)),
                arguments(true, employeeRequestListIncorrect, status().isBadRequest(), Set.of(EMPLOYEE_FIRST_NAME_EMPTY, EMPLOYEE_LAST_NAME_EMPTY, BU_CODE_EMPTY, BU_NAME_EMPTY, REGION_CODE_EMPTY, REGION_NAME_EMPTY)),
                arguments(true, employeeRequestListIncorrectSize, status().isBadRequest(), Set.of(EMPLOYEE_FIRST_NAME_SIZE, EMPLOYEE_LAST_NAME_SIZE, BU_CODE_SIZE, BU_NAME_SIZE, REGION_CODE_SIZE, REGION_NAME_SIZE))
        );
    }

    @ParameterizedTest
    @MethodSource("testData_saveMultipleEmployees")
    @DisplayName("call saveMultipleEmployees api with various requests")
    @SneakyThrows
    void test_saveMultipleEmployees(Boolean expectException, EmployeeRequestList employeeRequestList, ResultMatcher expectedStatus, Set exceptionMsg) {
        MvcResult mvcResult = mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequestList)))
                .andExpect(expectedStatus).andReturn();

        //BindingResult
        if (expectException) {
            Set<String> actualErrorKey = ((BindingResult) mvcResult.getResolvedException()).getAllErrors().stream().map(t -> t.getDefaultMessage()).collect(Collectors.toSet());
            assertEquals(exceptionMsg, actualErrorKey); //Compare 2 hashset --direct comparison no need to sort
        }
    }

    static Stream<Arguments> testData_deleteEmployeeList() {

        return Stream.of(
                arguments(true, null, status().isBadRequest(), Set.of(EMPLOYEE_LIST_ID)),
                arguments(false, "5,6", status().isOk(), null)
        );
    }

    @ParameterizedTest
    @MethodSource("testData_deleteEmployeeList")
    @DisplayName("call deleteEmployeeList api with various requests")
    @SneakyThrows
    void test_deleteEmployeeList(Boolean expectException, String id, ResultMatcher expectedStatus, Set exceptionMsg) {
        mockMvc.perform(delete("/employee")
                        .param("ids", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(expectedStatus);

    }

    static Stream<Arguments> testData_searchEmployeeById() {

        return Stream.of(
                arguments("", status().isBadRequest()),
                arguments(null, status().isBadRequest()),
                arguments("5", status().isOk(), null)
        );
    }

    @ParameterizedTest
    @MethodSource("testData_searchEmployeeById")
    @DisplayName("call searchEmployeeById api with various requests")
    @SneakyThrows
    void test_searchEmployeeById(String id, ResultMatcher expectedStatus) {
        mockMvc.perform(MockMvcRequestBuilders.get("/employee")
                        .param("id", id))
                .andExpect(expectedStatus);
    }

    @Test
    @DisplayName("call searchAllEmployees and expect status OK")
    @SneakyThrows
    void test_searchAllEmployees() {
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/all"))
                .andExpect(status().isOk());
    }


    static Stream<Arguments> testData_updateEmployeeList() {

        //Correct request
        EmployeeListUpdateRequest employeeListUpdateRequestCorrect = ControllerTestDataUtils.buildEmployeeListUpdateRequest();

        //Incorrect request -- employee update request set to null
        EmployeeListUpdateRequest employeeListUpdateRequestNotOk = ControllerTestDataUtils.buildEmployeeListUpdateRequest();
        employeeListUpdateRequestNotOk.setEmployeeUpdateRequests(null);

        //Incorrect request -- user id set to null
        EmployeeListUpdateRequest employeeListUpdateRequestNotId = ControllerTestDataUtils.buildEmployeeListUpdateRequest();
        employeeListUpdateRequestNotId.getEmployeeUpdateRequests().get(0).setUserId(null);

        return Stream.of(
                arguments(false, employeeListUpdateRequestCorrect, status().isOk(), null),
                arguments(true, employeeListUpdateRequestNotOk, status().isBadRequest(), EMPLOYEE_LIST_EMPTY),
                arguments(true, employeeListUpdateRequestNotId, status().isBadRequest(), ID_NULL)
        );
    }


    @ParameterizedTest
    @MethodSource("testData_updateEmployeeList")
    @DisplayName("Call updateEmployeeList api with various request")
    @SneakyThrows
    void test_updateEmployeeList(Boolean expectException, EmployeeListUpdateRequest employeeListUpdateRequest, ResultMatcher expectedStatus, String exceptionMsg) {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/employee")
                        .content(objectMapper.writeValueAsString((employeeListUpdateRequest)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(expectedStatus).andReturn();

        //BindingResult
        if (expectException) {
            BindingResult bindingResult = (BindingResult) mvcResult.getResolvedException();
            Assertions.assertEquals(exceptionMsg, bindingResult.getFieldError().getDefaultMessage());
        }
    }
}
