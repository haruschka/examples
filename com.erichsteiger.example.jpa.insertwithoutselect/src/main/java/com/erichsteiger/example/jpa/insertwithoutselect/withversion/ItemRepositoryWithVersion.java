package com.erichsteiger.example.jpa.insertwithoutselect.withversion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ItemRepositoryWithVersion extends JpaRepository<MyBusinessObjectWithVersion, String> {

}
