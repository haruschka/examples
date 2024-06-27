package com.erichsteiger.example.jpa.insertwithoutselect.withselect;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ItemRepository extends JpaRepository<MyBusinessObject, String> {

}
