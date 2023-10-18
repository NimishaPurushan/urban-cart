package com.example.urbancart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@EnableCaching
@SpringBootApplication
public class MainApplication {
  private static Logger logger = LogManager.getLogger(MainApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  @Bean
  ApplicationRunner applicationRunner(Environment environment) {
    return args -> {
      String port = environment.getProperty("local.server.port");
      logger.info("Swagger UI: http://localhost:{}/swagger-ui.html", port);
    };
  }
}
