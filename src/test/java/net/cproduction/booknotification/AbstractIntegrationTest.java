package net.cproduction.booknotification;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class AbstractIntegrationTest {
    static {
        System.getProperty("user.timezone", "Asia/Seoul");
    }

    @Container
    static final PostgreSQLContainer<?> postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer<>(
                DockerImageName.parse("postgres:latest"))
                .withDatabaseName("mydatabase")
                .withUsername("myuser")
                .withPassword("secret")
                .withEnv("TZ", "Asia/Seoul");

        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void registerPostgreProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }
}
