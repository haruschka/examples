package com.erichsteiger.example.jpa.manyindexes;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ManyIndexTestDAO1 extends JpaRepository<ManyIndexTestBO1, UUID>{
  List<ManyIndexTestBO1> findByStateAndModificationUserId(Integer i, String modificationUserId);
}
