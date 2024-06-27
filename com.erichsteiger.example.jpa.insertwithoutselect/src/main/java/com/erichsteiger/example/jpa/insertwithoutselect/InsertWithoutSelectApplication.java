package com.erichsteiger.example.jpa.insertwithoutselect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class InsertWithoutSelectApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(InsertWithoutSelectApplication.class, args);
    ctx.getBean(RunTest.class).run();
  }

}
