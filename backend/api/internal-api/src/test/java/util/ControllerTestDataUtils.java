package util;

import java.util.List;

import mu.management.employee.request.EmployeeListUpdateRequest;
import mu.management.employee.request.EmployeeRequest;
import mu.management.employee.request.EmployeeRequestList;
import mu.management.employee.request.EmployeeUpdateRequest;

/**
 * <p>Test data utility for unit testing</p>
 *
 * @author oudayrao.ittoo
 */
public class ControllerTestDataUtils {

    public static EmployeeRequest buildEmployeeRequest() {
        return EmployeeRequest.builder()
                .buCode("Sample bu code")
                .buName("Sample bu name")
                .lastName("Sample last name")
                .firstName("Sample first name")
                .regionName("Sample region name")
                .regionCode("Sample region code")
                .build();
    }

    public static EmployeeRequestList buildEmployeeRequestList() {
        return EmployeeRequestList.builder()
                .employeeRequestList(List.of(buildEmployeeRequest()))
                .build();
    }

    public static EmployeeUpdateRequest buildEmployeeUpdateRequest() {
        return EmployeeUpdateRequest.builder()
                .buCode("Sample bu code")
                .buName("Sample bu name")
                .lastName("Sample last name")
                .firstName("Sample first name")
                .regionName("Sample region name")
                .regionCode("Sample region code")
                .userId(2L)
                .build();
    }

    public static EmployeeListUpdateRequest buildEmployeeListUpdateRequest() {
        return EmployeeListUpdateRequest.builder()
                .employeeUpdateRequests(List.of(buildEmployeeUpdateRequest()))
                .build();
    }
}
