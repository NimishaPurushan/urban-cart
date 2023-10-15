package com.example.urbancart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.urbancart.controller.HelloController;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SmokeTest {
	@Autowired
	private HelloController helloController;

  @Test
  void contextLoads() {
	assertThat(helloController).isNotNull();
  }
}
