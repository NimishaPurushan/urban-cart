package com.example.urbancart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class MainApplication {
  private static Logger logger = LogManager.getLogger(MainApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  @Bean
  ApplicationRunner applicationRunner(Environment environment) {
    // Print environment and full url
    return args -> {
      String port = environment.getProperty("local.server.port");
      String activeProfile = environment.getProperty("spring.profiles.active");
      String logLevel = environment.getProperty("logging.level.org.springframework.web");
      logger.info("Application Profile: \"{}\"", activeProfile);
      logger.info("Log Level: \"{}\"", logLevel);
      logger.info("Swagger UI: http://localhost:{}/swagger-ui.html", port);
    };
  }
}
