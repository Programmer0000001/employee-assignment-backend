package mu.management.employee.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>Entity class for Employee</p>
 *
 * @author oudayrao.ittoo
 */
@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "bu_code")
    private String buCode;

    @Column(name = "bu_name")
    private String buName;

    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "region_name")
    private String regionName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(buCode, employee.buCode) && Objects.equals(buName, employee.buName) && Objects.equals(regionCode, employee.regionCode) && Objects.equals(regionName, employee.regionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, buCode, buName, regionCode, regionName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "  firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", buCode='" + buCode + '\'' +
                ", buName='" + buName + '\'' +
                ", regionCode='" + regionCode + '\'' +
                '}';
    }
}
