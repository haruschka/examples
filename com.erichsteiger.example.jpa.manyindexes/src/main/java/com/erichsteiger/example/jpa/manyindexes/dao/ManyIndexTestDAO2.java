package com.erichsteiger.example.jpa.manyindexes.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.erichsteiger.example.jpa.manyindexes.bo.ManyIndexTestBO2;

@Service
public interface ManyIndexTestDAO2 extends JpaRepository<ManyIndexTestBO2, UUID> {
	  List<ManyIndexTestBO2> findByStateAndModificationUserId(Integer i, String modificationUserId);
}
