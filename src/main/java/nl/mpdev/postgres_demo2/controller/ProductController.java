package nl.mpdev.postgres_demo2.controller;

import nl.mpdev.postgres_demo2.model.Product;
import nl.mpdev.postgres_demo2.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductRepository productRepository;

  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    var test =  productRepository.findById(id).orElseThrow(() -> new RuntimeException("test"));
    return ResponseEntity.ok().body(test);
  }

  @GetMapping
  public ResponseEntity<List<Product>> geAllProducts() {
    return ResponseEntity.ok(this.productRepository.findAll());
  }

  @PostMapping
  public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
    var test = productRepository.save(product);
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + test.getId()).toUriString());
    return ResponseEntity.created(uri).body(test);
  }
}