package com.product.tp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
     private String name;
     private double price;
     private String currency;
     private int tax;
     private String expired_at;
     private String produced_by;

     public String getDeleteUrl() {
          return "/products/delete/" + this.getId();
     }

}
