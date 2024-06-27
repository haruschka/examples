package com.erichsteiger.example.jpa.insertwithoutselect;

import java.math.BigDecimal;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erichsteiger.example.jpa.insertwithoutselect.withnewfield.ItemRepositoryWithNewField;
import com.erichsteiger.example.jpa.insertwithoutselect.withnewfield.MyBusinessObjectWithNewField;
import com.erichsteiger.example.jpa.insertwithoutselect.withselect.ItemRepository;
import com.erichsteiger.example.jpa.insertwithoutselect.withselect.MyBusinessObject;
import com.erichsteiger.example.jpa.insertwithoutselect.withversion.ItemRepositoryWithVersion;
import com.erichsteiger.example.jpa.insertwithoutselect.withversion.MyBusinessObjectWithVersion;

@Service
public class RunTest {
  private Logger LOGGER = LoggerFactory.getLogger(RunTest.class);
  @Autowired
  private ItemRepository repo1;
  @Autowired
  private ItemRepositoryWithNewField repo2;
  @Autowired
  private ItemRepositoryWithVersion repo3;

  public void run() {
    LOGGER.info("insert BO");

    LOGGER.info("default use case uses select before inserting records");
    MyBusinessObject bo1 = new MyBusinessObject(UUID.randomUUID(), "Saddle", 0, "Horseware");
    bo1.setAmount(new BigDecimal("3500.00"));
    repo1.save(bo1);

    LOGGER.info("in this case, new is tracked by transient field");
    MyBusinessObjectWithNewField bo2 = new MyBusinessObjectWithNewField(UUID.randomUUID(), "Saddle", 0, "Horseware");
    bo2.setAmount(new BigDecimal("3500.00"));
    repo2.save(bo2);

    LOGGER.info("uses @version annotation");
    MyBusinessObjectWithVersion bo3 = new MyBusinessObjectWithVersion(UUID.randomUUID(), "Saddle", 0, "Horseware");
    bo3.setAmount(new BigDecimal("3500.00"));
    repo3.save(bo3);
  }

}
