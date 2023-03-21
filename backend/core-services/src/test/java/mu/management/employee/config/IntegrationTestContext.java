package mu.management.employee.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>Configuration class for test</p>
 *
 * @author oudayrao.ittoo
 */
@Configuration
@ComponentScan(basePackages = {"mu.management.employee"})
@EntityScan(basePackages = {"mu.management.employee.entity"})
@EnableJpaRepositories(basePackages = {"mu.management.employee.repository"})
public abstract class IntegrationTestContext {

}
