package com.erichsteiger.example.jpa.manyindexes.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(indexes = {
    @Index(columnList = "modificationTs")})
public class ManyIndexTestBO2 extends AbstractBO {
 
}
