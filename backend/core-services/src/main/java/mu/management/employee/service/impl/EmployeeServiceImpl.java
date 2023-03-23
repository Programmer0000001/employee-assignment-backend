package mu.management.employee.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.management.employee.entity.Employee;
import mu.management.employee.exception.EmployeeManagementException;
import mu.management.employee.exception.EmployeeManagementMsgKey;
import mu.management.employee.mapper.EmployeeMapper;
import mu.management.employee.predicate.EmployeePredicateBuilder;
import mu.management.employee.repository.EmployeeRepository;
import mu.management.employee.request.EmployeeListUpdateRequest;
import mu.management.employee.request.EmployeeRequest;
import mu.management.employee.request.EmployeeRequestList;
import mu.management.employee.request.EmployeeSearchCriteria;
import mu.management.employee.response.EmployeeListResponse;
import mu.management.employee.response.EmployeeResponse;
import mu.management.employee.service.EmployeeService;

/**
 * <p>Implementation of {@link EmployeeService}</p>
 *
 * @author oudayrao.ittoo
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeePredicateBuilder employeePredicateBuilder;

    @Override
    @Transactional
    public List<Employee> saveMultipleEmployees(EmployeeRequestList employeeRequestList) {
        log.debug("Inside saveMultipleEmployees!");
        List<Employee> employeeList = new ArrayList<>();

        log.debug("Mapping request to entity");
        for (EmployeeRequest employeeRequest : employeeRequestList.getEmployeeRequestList()) {
            Employee employee = employeeMapper.toEmployee(employeeRequest);
            employeeList.add(employee);
        }

        log.debug("Saving {} employees to the database", employeeRequestList.getEmployeeRequestList().size());

        return employeeRepository.saveAll(employeeList);
    }

    @Override
    @Transactional
    public List<Employee> updateEmployeeList(EmployeeListUpdateRequest employeeListUpdateRequest) {
        log.debug("Inside updateEmployeeList!!");
        List<Employee> employeeList = new ArrayList<>();

        employeeListUpdateRequest.getEmployeeUpdateRequests().forEach(request -> {

            Employee employee = employeeRepository.findById(request.getUserId())
                    .orElseThrow(
                            () -> {
                                log.error("Employee with id {} not found", request.getUserId());
                                return new EmployeeManagementException(EmployeeManagementMsgKey.EMPLOYEE_NOT_FOUND, HttpStatus.NOT_FOUND, String.valueOf(request.getUserId()));
                            }
                    );
            employeeMapper.toEmployee(employee, request);
            employeeList.add(employee);
        });

        log.debug("Updating employees in progress");

        return employeeRepository.saveAll(employeeList);
    }

    @Override
    @Transactional
    public void deleteEmployeeList(List<Long> ids) {
        List<Long> missingEntities = employeeRepository.findMissingEntities(new HashSet<>(ids));

        if (!missingEntities.isEmpty()) {
            log.error("Employees with ids {} not found", missingEntities);
            throw new EmployeeManagementException(EmployeeManagementMsgKey.EMPLOYEE_NOT_FOUND, HttpStatus.NOT_FOUND, String.valueOf(missingEntities));
        }

        employeeRepository.deleteAllById(ids);
    }

    @Override
    public EmployeeResponse searchEmployeeById(Long id) {
        log.debug("Searching employee with id {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(
                        () -> {
                            log.error("Employee with id {} not found", id);
                            return new EmployeeManagementException(EmployeeManagementMsgKey.EMPLOYEE_NOT_FOUND, HttpStatus.NOT_FOUND, String.valueOf(id));
                        }
                );

        log.debug("Employee with id {} found", id);

        return employeeMapper.toEmployeeResponse(employee);
    }

    @Override
    public List<EmployeeResponse> searchAllEmployees() {
        log.info("Inside searchAllEmployees");

        return employeeMapper.toEmployeeResponse(employeeRepository.findAll());
    }

    @Override
    public EmployeeListResponse searchEmployeeByFilter(EmployeeSearchCriteria employeeSearchCriteria) {
        log.info("Retrieving employee with EmployeeSearchCriteria {}", employeeSearchCriteria);

        //Build page request
        PageRequest pageRequest = PageRequest.of(employeeSearchCriteria.getPageNumber(), employeeSearchCriteria.getPageSize(), employeeSearchCriteria.getSortOrder().equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, employeeSearchCriteria.getSortBy());

        log.info("Building employee search criteria for EmployeeSearchCriteria {}", employeeSearchCriteria);

        //Find all employees matching the search criteria
        Page<Employee> employeePage = employeeRepository.findAll(employeePredicateBuilder.buildEmployeeFilter(employeeSearchCriteria), pageRequest);

        log.info("Mapping retrieve employee from db to response for EmployeeSearchCriteria {}", employeeSearchCriteria);
        EmployeeListResponse employeeListResponse = EmployeeListResponse.builder().employeeResponses(employeeMapper.toEmployeeResponse(employeePage.getContent())).build();

        log.info("Total element found {} for EmployeeSearchCriteria {}", employeePage.getTotalElements(), employeeSearchCriteria);
        employeeListResponse.setTotalElement(employeePage.getTotalElements());

        return employeeListResponse;
    }
}
