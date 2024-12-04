package br.com.er.utils;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class TestContainersConfig implements QuarkusTestResourceLifecycleManager {

    private static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:15");
    private GenericContainer<?> postgresContainer = new GenericContainer<>(POSTGRES_IMAGE);

    @Override
    public Map<String, String> start() {
        postgresContainer.withEnv("POSTGRES_USER", "adm")
                        .withEnv("POSTGRES_PASSWORD", "qwe123")
                        .withEnv("POSTGRES_DB", "db-livro")
                        .withExposedPorts(5432);
        postgresContainer.start();

        Map<String, String> properties = new HashMap<>();
        properties.put("quarkus.datasource.username", "adm");
        properties.put("quarkus.datasource.password", "qwe123");
        properties.put("quarkus.datasource.jdbc.url",
            "jdbc:postgresql://"+ postgresContainer.getHost() +":" + postgresContainer.getFirstMappedPort() + "/db-livro");
        
        return properties;
    }

    @Override
    public void stop() {
    }
    
}
