package com.example.urbancart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

  @GetMapping
  public String helloWorld(@RequestParam(required = false, defaultValue = "World") String name) {
    return String.format("Hello, %s!", name);
  }
}
