package com.product.tp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.tp.models.Product;
import com.product.tp.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
     private ProductService productService;

     @Autowired
     public ProductController(ProductService productService) {
          this.productService = productService;
     }

     @GetMapping
     public List<Product> all() {
          return this.productService.getAllProducts();
     }

     @PostMapping
     public Product create(@RequestBody Product product) {
          this.productService.createProduct(product);
          return product;
     }

     @DeleteMapping("/{id}")
     public String delete(@PathVariable("id") Long id) {
          this.productService.deleteProducts(id);
          return "Deleted";
     }

     @GetMapping("/{id}")
     public ResponseEntity<? extends Object> product(@PathVariable("id") Long id) {
          return this.productService.getProduct(id);
     }

     @PatchMapping("/{id}")
     public ResponseEntity<? extends Object> update(@PathVariable("id") Long id, @RequestBody Product product) {
          return this.productService.updateProduct(id, product);
     }
}
