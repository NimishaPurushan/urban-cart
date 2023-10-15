package com.example.urbancart;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.urbancart.controller.HelloController;
import com.example.urbancart.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmokeTest {
  @Autowired private HelloController helloController;
  @Autowired private ProductController productController;

  @Test
  void contextLoads() {
    assertThat(helloController).isNotNull();
    assertThat(productController).isNotNull();
  }

  // @Test
  // public void applicationContextTest() {
  //   MainApplication.main(new String[] {});
  // }
}
