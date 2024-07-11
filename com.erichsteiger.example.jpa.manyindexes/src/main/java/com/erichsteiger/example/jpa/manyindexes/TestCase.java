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

import com.erichsteiger.example.jpa.manyindexes.bo.AbstractBO;
import com.erichsteiger.example.jpa.manyindexes.bo.IBO;
import com.erichsteiger.example.jpa.manyindexes.bo.ManyIndexTestBO1;
import com.erichsteiger.example.jpa.manyindexes.bo.ManyIndexTestBO2;
import com.erichsteiger.example.jpa.manyindexes.bo.ManyIndexTestBO3;
import com.erichsteiger.example.jpa.manyindexes.dao.ManyIndexTestDAO1;
import com.erichsteiger.example.jpa.manyindexes.dao.ManyIndexTestDAO2;
import com.erichsteiger.example.jpa.manyindexes.dao.ManyIndexTestDAO3;
import com.erichsteiger.example.jpa.manyindexes.statistics.Statistics;

@Service
public class TestCase {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestCase.class);
  @Autowired
  private ManyIndexTestDAO1 dao1;
  @Autowired
  private ManyIndexTestDAO2 dao2;
  @Autowired
  private ManyIndexTestDAO3 dao3;
  @Autowired
  private Statistics statistics;
  private Random random = new Random();

  private void initBO(AbstractBO bo) {
    bo.setFirstName("" + UUID.randomUUID());
    bo.setLastName("" + UUID.randomUUID());
    bo.setCreationTs(LocalDateTime.now());
    bo.setModificationTs(LocalDateTime.now());
    bo.setModificationUserId("Thread-" + Thread.currentThread().getId());
    bo.setState(0);
  }

  public void run() {
    warmUp(1000);
    for (int i = 0; i < 10; i++) {
      testInsertWithoutIndexes(1000);
      testInsertWith1Indexes(1000);
      testInsertWithIndexes(1000);

      testUpdateWithoutIndexes();
      testUpdatetWith1Index();
      testUpdatetWithIndexes();
    }
  }

  private void testInsertWith1Indexes(int cycles) {
    LocalDateTime startTime = LocalDateTime.now();
    for (int i = 0; i < cycles; i++) {
      ManyIndexTestBO2 bo = new ManyIndexTestBO2();
      initBO(bo);
      dao2.save(bo);
    }
    statistics.addStats("Insert With-1-Indexes", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
    LOGGER.info("duration insert with 1 index = {}", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));

  }

  private void testInsertWithIndexes(int cycles) {
    LocalDateTime startTime = LocalDateTime.now();
    for (int i = 0; i < cycles; i++) {
      ManyIndexTestBO3 bo = new ManyIndexTestBO3();
      initBO(bo);
      dao3.save(bo);
    }
    statistics.addStats("Insert With-12-Indexes", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
    LOGGER.info("duration insert with 12 indexes = {}", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
  }

  private void testInsertWithoutIndexes(int cycles) {
    LocalDateTime startTime = LocalDateTime.now();
    for (int i = 0; i < cycles; i++) {
      ManyIndexTestBO1 bo = new ManyIndexTestBO1();
      initBO(bo);
      dao1.save(bo);
    }
    statistics.addStats("Insert With-0-Indexes", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
    LOGGER.info("duration insert without indexes  = {}", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
  }

  private void testUpdatetWith1Index() {
    List<ManyIndexTestBO2> all = dao2.findByStateAndModificationUserId(0,
        "Thread-" + Thread.currentThread().getId());
    LOGGER.debug("starting update {} rows", all.size());
    LocalDateTime startTime = LocalDateTime.now();
    for (ManyIndexTestBO2 bo : all) {
      updateBO(bo);
      dao2.save(bo);
    }
    statistics.addStats("Update-With-1-Index", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
    LOGGER.info("duration update with 1- index = {}", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));

  }

  private void testUpdatetWithIndexes() {
    List<ManyIndexTestBO3> all = dao3.findByStateAndModificationUserId(0,
        "Thread-" + Thread.currentThread().getId());
    LOGGER.debug("starting update {} rows", all.size());
    LocalDateTime startTime = LocalDateTime.now();
    for (ManyIndexTestBO3 bo : all) {
      updateBO(bo);
      dao3.save(bo);
    }
    statistics.addStats("Update-With-12-Indexes", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
    LOGGER.info("duration update with 12 indexes = {}", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
  }

  private void testUpdateWithoutIndexes() {
    List<ManyIndexTestBO1> all = dao1.findByStateAndModificationUserId(0,
        "Thread-" + Thread.currentThread().getId());
    LOGGER.debug("starting update {} rows", all.size());
    LocalDateTime startTime = LocalDateTime.now();
    for (ManyIndexTestBO1 bo : all) {
      updateBO(bo);
      dao1.save(bo);
    }
    statistics.addStats("Update With-0-Indexes", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));
    LOGGER.info("duration update without indexes = {}", ChronoUnit.MILLIS.between(startTime, LocalDateTime.now()));

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

  private void warmUp(int cycles) {
    for (int i = 0; i < cycles; i++) {
      ManyIndexTestBO1 bo = new ManyIndexTestBO1();
      initBO(bo);
      dao1.save(bo);
      ManyIndexTestBO2 bo2 = new ManyIndexTestBO2();
      initBO(bo);
      dao2.save(bo2);
      ManyIndexTestBO3 bo3 = new ManyIndexTestBO3();
      initBO(bo);
      dao3.save(bo3);

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

    List<ManyIndexTestBO3> all3 = dao3.findByStateAndModificationUserId(0, "Thread" + Thread.currentThread().getId());
    for (ManyIndexTestBO3 bo : all3) {
      updateBO(bo);
      dao3.save(bo);
    }
  }

}
