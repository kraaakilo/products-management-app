package com.product.tp.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.product.tp.dto.ProductDTO;
import com.product.tp.models.Product;
import com.product.tp.repositories.ProductRepository;
import com.product.tp.services.ProductService;

@Controller
public class MVCController {
     private ProductService productService;
     private ProductRepository productRepository;

     @Autowired
     public MVCController(ProductService productService, ProductRepository productRepository) {
          this.productService = productService;
          this.productRepository = productRepository;
     }

     @GetMapping("/products")
     public String home(Model model) {
          model.addAttribute("products", productService.getAllProducts());
          model.addAttribute("count", productService.getAllProducts().size());
          return "home";
     }

     @GetMapping("/products/create")
     public String create() {
          return "add";
     }

     @GetMapping("/products/edit/{id}")
     public String update(Model model, @PathVariable("id") Long id) {
          Optional<Product> product = productRepository.findById(id);
          model.addAttribute("product", product.get());
          return "edit";
     }

     @PostMapping("/products/update/{id}")
     public RedirectView update(@ModelAttribute @Validated ProductDTO productDTO, BindingResult result,
               Model model,
               RedirectAttributes redirectAttributes, @PathVariable Long id) {
          ArrayList<String> errors = new ArrayList<>();
          if (result.hasErrors()) {
               for (ObjectError obj : result.getAllErrors()) {
                    errors.add(obj.getDefaultMessage());
               }
               redirectAttributes.addFlashAttribute("errors", errors);
          } else {
               redirectAttributes.addFlashAttribute("success", true);
               this.productService.updateProduct(id, productDTO.toProduct());
          }
          return new RedirectView("/products/edit/" + id);
     }

     @PostMapping("/products/create")
     public RedirectView create(@ModelAttribute @Validated ProductDTO productDTO, BindingResult result, Model model,
               RedirectAttributes redirectAttributes) {
          ArrayList<String> errors = new ArrayList<>();
          if (result.hasErrors()) {
               for (ObjectError obj : result.getAllErrors()) {
                    errors.add(obj.getDefaultMessage());
               }
               redirectAttributes.addFlashAttribute("errors", errors);
          } else {
               this.productService.createProduct(productDTO.toProduct());
          }
          return new RedirectView("/products");
     }

     @GetMapping("/products/delete/{id}")
     public RedirectView delete(@PathVariable Long id) {
          this.productService.deleteProducts(id);
          return new RedirectView("/products");
     }
}
