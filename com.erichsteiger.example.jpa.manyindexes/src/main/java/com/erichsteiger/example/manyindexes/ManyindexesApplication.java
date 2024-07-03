package com.erichsteiger.example.manyindexes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ManyindexesApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ManyindexesApplication.class, args);
		ctx.getBean(TestCase.class).run();
	}

}
