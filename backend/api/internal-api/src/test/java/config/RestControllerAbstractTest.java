package config;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import mu.management.employee.controller.EmployeeController;

/**
 * <p>Class for controller containing common configurations</p>
 * <p>{@link RestControllerMockAbstractTest} extends this class to inherit the configurations</p>
 *
 * @author oudayrao.ittoo
 */
@WebMvcTest(excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ContextConfiguration(classes = {EmployeeController.class})
public class RestControllerAbstractTest {
}
