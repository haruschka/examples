package com.erichsteiger.example.manyindexes;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table
public class ManyIndexTestBO1 implements IBO {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String firstName;
  private String lastName;
  private LocalDateTime creationTs;
  private LocalDateTime modificationTs;
  private Integer state;
  private String street;
  private String postalCode;
  private String city;
  @Version
  private Long version;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDateTime getCreationTs() {
    return creationTs;
  }

  public void setCreationTs(LocalDateTime creationTs) {
    this.creationTs = creationTs;
  }

  public LocalDateTime getModificationTs() {
    return modificationTs;
  }

  public void setModificationTs(LocalDateTime modificationTs) {
    this.modificationTs = modificationTs;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
