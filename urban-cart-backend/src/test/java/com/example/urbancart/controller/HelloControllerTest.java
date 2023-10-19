package com.example.urbancart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  public void testHelloWorld() throws Exception {
    mockMvc
        .perform(get("/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string("Hello, World!"));
  }

  @ParameterizedTest
  @CsvSource(
      value = {
        "World\tHello, World!",
        "John\tHello, John!",
        "Jane\tHello, Jane!",
        "J Doe\tHello, J Doe!"
      },
      delimiter = '\t')
  public void testHelloName(String name, String expected) throws Exception {
    System.out.println("name: " + name);
    System.out.println("expected: " + expected);
    mockMvc
        .perform(get("/hello").param("name", name))
        .andExpect(status().isOk())
        .andExpect(content().string(expected));
  }

  @Test
  public void testIgnoresExtraParameter() throws Exception {
    mockMvc
        .perform(get("/hello").param("name", "John").param("age", "42"))
        .andExpect(status().isOk())
        .andExpect(content().string("Hello, John!"));
  }
}
