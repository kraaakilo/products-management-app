package com.product.tp.dto;

import org.hibernate.validator.constraints.Length;

import com.product.tp.models.Product;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductDTO {
     @Length(min = 2, message = "Le champ Nom est obligatoire.")
     private String name;

     @Min(value = 0, message = "Veuillez saisir un Prix valide.")
     private double price;

     @Length(min = 2, message = "Le champ Devise est obligatoire.")
     private String currency;

     @Min(value = 0, message = "Veuillez saisir un Taux de taxe valide.")
     private int tax;

     @Length(min = 2, message = "Veuillez saisir une Date d'expiration valide au format AAAA-MM-JJ.")
     private String expired_at;

     @Length(min = 2, message = "Le champ Produit par est obligatoire.")
     private String produced_by;

     public Product toProduct() {
          Product product = new Product();
          product.setName(this.name);
          product.setCurrency(this.currency);
          product.setExpired_at(this.expired_at);
          product.setPrice(this.price);
          product.setProduced_by(this.produced_by);
          product.setTax(this.tax);
          return product;
     }
}
