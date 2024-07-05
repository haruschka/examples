package com.erichsteiger.example.jpa.manyindexes;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.erichsteiger.example.jpa.manyindexes.statistics.Statistics;

@SpringBootApplication
public class ManyindexesApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(ManyindexesApplication.class);

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(ManyindexesApplication.class, args);

    int noOfThreads = Integer.valueOf(ctx.getEnvironment().getProperty("example.jpa.manyindexes.threads", "1"));
    List<Thread> threads = new ArrayList<>();
    for (int i = 0; i < noOfThreads; i++) {
      Thread t = new Thread(() -> ctx.getBean(TestCase.class).run());
      threads.add(t);
      t.start();
    }
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        LOGGER.error(e.getMessage(), e);
      }
    }
    ctx.getBean(Statistics.class).print();

  }

}
