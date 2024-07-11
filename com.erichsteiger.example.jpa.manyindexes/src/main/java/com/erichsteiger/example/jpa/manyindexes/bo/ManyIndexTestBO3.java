package com.erichsteiger.example.jpa.manyindexes.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(indexes = {
    @Index(columnList = "firstName"),
    @Index(columnList = "lastName"),
    @Index(columnList = "creationTs"),
    @Index(columnList = "modificationTs"),
    @Index(columnList = "state"),
    @Index(columnList = "street"),
    @Index(columnList = "postalCode"),
    @Index(columnList = "postalCode,state,city"),
    @Index(columnList = "postalCode,modificationTs,city"),
    @Index(columnList = "postalCode,city"),
    @Index(columnList = "lastName,firstName"),
    @Index(columnList = "state,modificationUserId")})
public class ManyIndexTestBO3 extends AbstractBO {
 
}
