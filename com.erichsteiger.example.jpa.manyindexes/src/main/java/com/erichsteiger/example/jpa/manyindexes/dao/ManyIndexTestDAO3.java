package com.erichsteiger.example.jpa.manyindexes.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.erichsteiger.example.jpa.manyindexes.bo.ManyIndexTestBO3;

@Service
public interface ManyIndexTestDAO3 extends JpaRepository<ManyIndexTestBO3, UUID> {
	  List<ManyIndexTestBO3> findByStateAndModificationUserId(Integer i, String modificationUserId);
}
