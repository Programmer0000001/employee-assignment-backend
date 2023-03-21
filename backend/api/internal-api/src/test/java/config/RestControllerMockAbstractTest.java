package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mu.management.employee.service.EmployeeService;

/**
 * <p>Class containing common mock bean</p>
 *
 * @author oudayrao.ittoo
 */
public class RestControllerMockAbstractTest extends RestControllerAbstractTest {
    @MockBean
    protected EmployeeService employeeService;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
}
