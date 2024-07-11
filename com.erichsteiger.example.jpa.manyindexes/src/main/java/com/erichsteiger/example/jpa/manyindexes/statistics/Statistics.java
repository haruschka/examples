package com.erichsteiger.example.jpa.manyindexes.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Statistics {
  private static final Logger LOGGER = LoggerFactory.getLogger(Statistics.class);
  private Map<String, List<Long>> stats = new HashMap<>();

  public void addStats(String string, long duration) {
    synchronized (stats) {
      if (stats.containsKey(string)) {
        stats.get(string).add(duration);
      } else {
        stats.put(string, new ArrayList<>());
        stats.get(string).add(duration);
      }
    }
  }

  public void print() {
    for (Map.Entry<String, List<Long>> entry : stats.entrySet()) {
      List<Long> val = entry.getValue();
      Long avg = 0L;
      for (Long long1 : val) {
        avg += long1;
      }
      LOGGER.info("{} avg: {}", entry.getKey(), avg / val.size());
    }
  }

}
