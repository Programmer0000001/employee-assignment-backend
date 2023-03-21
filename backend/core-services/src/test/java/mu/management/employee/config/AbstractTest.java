package mu.management.employee.config;

import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

/** <p>Class handling test container</p>
 *
 * @author oudayrao.ittoo
 */
@DataJpaTest
@ActiveProfiles({"test"}) //set profile --read application-test.yml
@ContextConfiguration(classes = IntegrationTestContext.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AbstractTest {
    private static final String MYSQL_DOCKER_IMAGE = "mysql:latest";
    private static final String DATABASE_NAME = "mysql";

    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(MYSQL_DOCKER_IMAGE)
            .withDatabaseName(DATABASE_NAME)
            .withTmpFs(Collections.singletonMap("/testTmpFs", "rw"))
            .withReuse(true);

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> mySQLContainer.getJdbcUrl());
        registry.add("spring.datasource.password", mySQLContainer::getUsername);
        registry.add("spring.datasource.username", mySQLContainer::getPassword);
    }

    @BeforeAll
    static void setUp() {
        mySQLContainer.start();
    }
}
