package mu.management.employee.utils;

import java.util.List;

import mu.management.employee.request.EmployeeListUpdateRequest;
import mu.management.employee.request.EmployeeRequest;
import mu.management.employee.request.EmployeeRequestList;
import mu.management.employee.request.EmployeeUpdateRequest;

/**
 * <p>Utility class providing helper methods for testing purposes</p>
 *
 * @author oudayrao.ittoo
 */
public class EmployeeTestDataUtil {
    public static EmployeeRequest buildEmployeeRequest() {
        return EmployeeRequest.builder()
                .buCode("125896")
                .buName("Bu name")
                .lastName("uzumaki")
                .firstName("naruto")
                .regionName("Moka")
                .regionCode("88888")
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
