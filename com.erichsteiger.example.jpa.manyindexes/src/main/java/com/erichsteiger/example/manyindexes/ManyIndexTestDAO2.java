package com.erichsteiger.example.manyindexes;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ManyIndexTestDAO2 extends JpaRepository<ManyIndexTestBO2, UUID> {
  List<ManyIndexTestBO2> findByState(Integer i);
}
