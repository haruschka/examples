package com.erichsteiger.example.jpa.manyindexes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erichsteiger.example.jpa.manyindexes.statistics.Statistics;

@Service
public class TestCase {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestCase.class);
  @Autowired
  private ManyIndexTestDAO1 dao1;
  @Autowired
  private ManyIndexTestDAO2 dao2;
  @Autowired
  private Statistics statistics;
  private Random random = new Random();

  public void run() {
    warmUp(1000);
    for (int i = 0; i < 10; i++) {
      testInsertWithoutIndexes(1000);
      testInsertWithIndexes(1000);

      testUpdatetWithIndexes();
      testUpdateWithoutIndexes();
    }
  }

  private void testUpdatetWithIndexes() {
    List<ManyIndexTestBO1> all = dao1.findByStateAndModificationUserId(0,
        "Thread" + Thread.currentThread().getId());
    LOGGER.debug("starting update {} rows", all.size());
    LocalDateTime startTime = LocalDateTime.now();
    for (ManyIndexTestBO1 bo : all) {
      updateBO(bo);
      dao1.save(bo);
    }
    statistics.addStats("Update-With-Indexes", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
    LOGGER.info("duration update with indexes = {}", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
  }

  private void updateBO(IBO bo) {
    bo.setFirstName(bo.getFirstName().toUpperCase());
    bo.setLastName(bo.getLastName().toUpperCase());
    bo.setModificationTs(LocalDateTime.now());
    bo.setPostalCode("" + random.nextInt(9999 - 1000 + 1) + 1000);
    bo.setCity(bo.getPostalCode() + " City");
    bo.setStreet("AnyStreet " + random.nextInt(9999 - 1000 + 1) + 1000);
    bo.setState(1);
  }

  private void testUpdateWithoutIndexes() {
    List<ManyIndexTestBO2> all = dao2.findByStateAndModificationUserId(0,
        "Thread" + Thread.currentThread().getId());
    LOGGER.debug("starting update {} rows", all.size());
    LocalDateTime startTime = LocalDateTime.now();
    for (ManyIndexTestBO2 bo : all) {
      updateBO(bo);
      dao2.save(bo);
    }
    statistics.addStats("Update Without-Indexes", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
    LOGGER.info("duration update without indexes = {}", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));

  }

  private void warmUp(int cycles) {
    for (int i = 0; i < cycles; i++) {
      ManyIndexTestBO2 bo = new ManyIndexTestBO2();
      bo.setFirstName("" + UUID.randomUUID());
      bo.setLastName("" + UUID.randomUUID());
      bo.setCreationTs(LocalDateTime.now());
      bo.setModificationTs(LocalDateTime.now());
      bo.setState(0);
      dao2.save(bo);
      ManyIndexTestBO1 bo2 = new ManyIndexTestBO1();
      bo2.setFirstName("" + UUID.randomUUID());
      bo2.setLastName("" + UUID.randomUUID());
      bo2.setCreationTs(LocalDateTime.now());
      bo2.setModificationTs(LocalDateTime.now());
      bo2.setState(0);
      dao1.save(bo2);

    }
    List<ManyIndexTestBO1> all = dao1.findByStateAndModificationUserId(0, "Thread" + Thread.currentThread().getId());
    for (ManyIndexTestBO1 bo : all) {
      updateBO(bo);
      dao1.save(bo);
    }

    List<ManyIndexTestBO2> all2 = dao2.findByStateAndModificationUserId(0, "Thread" + Thread.currentThread().getId());
    for (ManyIndexTestBO2 bo : all2) {
      updateBO(bo);
      dao2.save(bo);
    }
  }

  private void testInsertWithIndexes(int cycles) {
    LocalDateTime startTime = LocalDateTime.now();
    for (int i = 0; i < cycles; i++) {
      ManyIndexTestBO2 bo = new ManyIndexTestBO2();
      bo.setFirstName("" + UUID.randomUUID());
      bo.setLastName("" + UUID.randomUUID());
      bo.setCreationTs(LocalDateTime.now());
      bo.setModificationTs(LocalDateTime.now());
      bo.setModificationUserId("Thread" + Thread.currentThread().getId());
      bo.setState(0);
      dao2.save(bo);
    }
    statistics.addStats("Insert With-Indexes", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
    LOGGER.info("duration insert with indexes = {}", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
  }

  private void testInsertWithoutIndexes(int cycles) {
    LocalDateTime startTime = LocalDateTime.now();
    for (int i = 0; i < cycles; i++) {
      ManyIndexTestBO1 bo = new ManyIndexTestBO1();
      bo.setFirstName("" + UUID.randomUUID());
      bo.setLastName("" + UUID.randomUUID());
      bo.setCreationTs(LocalDateTime.now());
      bo.setModificationTs(LocalDateTime.now());
      bo.setModificationUserId("Thread" + Thread.currentThread().getId());
      bo.setState(0);
      dao1.save(bo);
    }
    statistics.addStats("Insert Without-Indexes", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
    LOGGER.info("duration insert without indexes  = {}", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
  }

}
