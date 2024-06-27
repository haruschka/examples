package com.erichsteiger.example.jpa.insertwithoutselect.withnewfield;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ItemRepositoryWithNewField extends JpaRepository<MyBusinessObjectWithNewField, String> {

}
