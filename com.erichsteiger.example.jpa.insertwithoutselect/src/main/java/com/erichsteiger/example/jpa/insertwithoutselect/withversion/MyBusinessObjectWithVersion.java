package com.erichsteiger.example.jpa.insertwithoutselect.withversion;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class MyBusinessObjectWithVersion {
  @Id
  private UUID uuid;
  private String name;
  private int quantity;
  private BigDecimal amount;
  private String category;
  @Version
  private Long version;

  public MyBusinessObjectWithVersion() {

  }

  public MyBusinessObjectWithVersion(UUID uuid, String name, int quantity, String category) {
    super();
    this.uuid = uuid;
    this.name = name;
    this.quantity = quantity;
    this.category = category;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String toString() {
    return "id: " + uuid + ", name: " + name + ", price: " + amount;
  }

}
