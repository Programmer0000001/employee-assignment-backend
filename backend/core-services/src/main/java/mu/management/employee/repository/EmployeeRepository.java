package mu.management.employee.repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mu.management.employee.entity.Employee;

/**
 * <p>Repository class for {@link Employee} entity</p>
 *
 * @author oudayrao.ittoo
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * <p>Custom function to find missing entities given their ids</p>
     *
     * @param ids Ids of employees
     * @return Missing ids
     */
    default List<Long> findMissingEntities(Set<Long> ids) {
        return ids.stream()
                .filter(id -> !findById(id).isPresent())
                .collect(Collectors.toList());
    }
}
