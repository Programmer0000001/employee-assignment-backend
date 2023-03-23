package mu.management.employee.predicate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;
import mu.management.employee.entity.QEmployee;
import mu.management.employee.request.EmployeeSearchCriteria;

/**
 * Class to build predicates needed for Employee search api
 *
 * @author oudayrao.ittoo
 */
@Component
@RequiredArgsConstructor
public class EmployeePredicateBuilder {

    /**
     * <p>Filter to search for employee given {@link mu.management.employee.request.EmployeeSearchCriteria}</p>
     *
     * @param employeeSearchCriteria {@link EmployeeSearchCriteria}
     * @return {@link BooleanBuilder}
     */
    public BooleanBuilder buildEmployeeFilter(EmployeeSearchCriteria employeeSearchCriteria) {
        BooleanBuilder employeePredicate = new BooleanBuilder();
        List<Predicate> predicateList = new ArrayList<>();

        if (StringUtils.hasText(employeeSearchCriteria.getFirstName())) {
            predicateList.add(QEmployee.employee.firstName.equalsIgnoreCase(employeeSearchCriteria.getFirstName()));
        }

        if (StringUtils.hasText(employeeSearchCriteria.getLastName())) {
            predicateList.add(QEmployee.employee.lastName.equalsIgnoreCase(employeeSearchCriteria.getLastName()));
        }

        if (StringUtils.hasText(employeeSearchCriteria.getBuCode())) {
            predicateList.add(QEmployee.employee.buCode.equalsIgnoreCase(employeeSearchCriteria.getBuCode()));
        }

        if (StringUtils.hasText(employeeSearchCriteria.getBuName())) {
            predicateList.add(QEmployee.employee.buName.equalsIgnoreCase(employeeSearchCriteria.getBuName()));
        }

        if (StringUtils.hasText(employeeSearchCriteria.getRegionCode())) {
            predicateList.add(QEmployee.employee.regionCode.equalsIgnoreCase(employeeSearchCriteria.getRegionCode()));
        }

        if (StringUtils.hasText(employeeSearchCriteria.getRegionName())) {
            predicateList.add(QEmployee.employee.regionName.equalsIgnoreCase(employeeSearchCriteria.getRegionName()));
        }

        if (null != employeeSearchCriteria.getUserId()) {
            predicateList.add(QEmployee.employee.userId.eq(employeeSearchCriteria.getUserId()));
        }

        if (!predicateList.isEmpty()) {
            employeePredicate.orAllOf(predicateList.toArray(new Predicate[0]));
        }

        return employeePredicate;
    }
}
