package mu.management.employee.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

/**
 * <p>Entity class containing common attributes</p>
 *
 * @author oudayrao.ittoo
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {

    //Auto generate created date
    @CreationTimestamp
    @Column(name = "created_date")
    LocalDateTime createdDate;
}
