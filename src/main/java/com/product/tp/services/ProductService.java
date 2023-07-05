package com.product.tp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.product.tp.models.Product;
import com.product.tp.repositories.ProductRepository;

@Component
public class ProductService {
     private ProductRepository productRepository;

     @Autowired
     public ProductService(ProductRepository productRepository) {
          this.productRepository = productRepository;
     }

     public void createProduct(Product product) {
          productRepository.save(product);
     }

     public List<Product> getAllProducts() {
          return this.productRepository.findAll();
     }

     public ResponseEntity<? extends Object> getProduct(Long id) {
          Optional<Product> productOptional = this.productRepository.findById(id);
          if (productOptional.isPresent()) {
               ResponseEntity<Product> response = ResponseEntity.ok(productOptional.get());
               return response;
          } else {
               HashMap<String, String> r = new HashMap<>();
               r.put("message", "Produit non trouvé");
               ResponseEntity<HashMap<String, String>> response = ResponseEntity.ok(r);
               return response;
          }
     }

     public ResponseEntity<? extends Object> updateProduct(Long id, Product product) {
          Optional<Product> productOptional = this.productRepository.findById(id);
          if (productOptional.isPresent()) {
               Product updatedProduct = productOptional.get();
               updatedProduct.setName(product.getName());
               updatedProduct.setCurrency(product.getCurrency());
               updatedProduct.setTax(product.getTax());
               updatedProduct.setExpired_at(product.getExpired_at());
               updatedProduct.setProduced_by(product.getProduced_by());
               this.productRepository.save(updatedProduct);
               ResponseEntity<Product> response = ResponseEntity.ok(updatedProduct);
               return response;
          } else {
               HashMap<String, String> r = new HashMap<>();
               r.put("message", "Produit non trouvé");
               ResponseEntity<HashMap<String, String>> response = ResponseEntity.ok(r);
               return response;
          }
     }

     public void deleteProducts(Long id) {
          this.productRepository.deleteById(id);
     }
}
