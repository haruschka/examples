package com.erichsteiger.example.manyindexes;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IBO {

  UUID getId();

  void setId(UUID id);

  String getFirstName();

  void setFirstName(String firstName);

  String getLastName();

  void setLastName(String lastName);

  LocalDateTime getCreationTs();

  void setCreationTs(LocalDateTime creationTs);

  LocalDateTime getModificationTs();

  void setModificationTs(LocalDateTime modificationTs);

  Integer getState();

  public void setState(Integer state);

  String getStreet();

  void setStreet(String street);

  String getPostalCode();

  void setPostalCode(String postalCode);

  String getCity();

  void setCity(String city);
}
