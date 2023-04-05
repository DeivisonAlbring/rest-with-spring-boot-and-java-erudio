package br.com.erudio.integrationtests.testcontainers;

import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		
		private Logger logger = Logger.getLogger(AbstractIntegrationTest.class.getName());
		
		static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.32");
		
		private static void startContainers() {
			Startables.deepStart(Stream.of(mysql)).join();
		}

		@DynamicPropertySource
		private static Map<String, String> createConnectionConfiguration() {
			return Map.of(
				"spring.datasource.url", mysql.getJdbcUrl(),
				"spring.datasource.username", mysql.getUsername(),
				"spring.datasource.password", mysql.getPassword()
			);
		}
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			startContainers();
			ConfigurableEnvironment environment = applicationContext.getEnvironment();
			logger.info("user: "+mysql.getUsername());
			logger.info("Pass: "+mysql.getPassword());
			logger.info("URL: "+mysql.getJdbcUrl());	
			
			MapPropertySource testcontainers = new MapPropertySource(
				"testcontainers",
				(Map) createConnectionConfiguration());
			environment.getPropertySources().addFirst(testcontainers);
			
			
		}
	}
}
