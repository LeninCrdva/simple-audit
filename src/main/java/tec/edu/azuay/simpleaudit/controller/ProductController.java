package tec.edu.azuay.simpleaudit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tec.edu.azuay.simpleaudit.enties.Product;
import tec.edu.azuay.simpleaudit.repository.IProductRepository;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductRepository productRepository;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/get-all")
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{codeProduct}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer codeProduct, @RequestBody Product product) {
        Product productToUpdate = productRepository.findById(codeProduct).orElseThrow(() -> new RuntimeException("Product not found"));

        productToUpdate.setName(product.getName());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setPrice(product.getPrice());
        productRepository.save(productToUpdate);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product updated successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{codeProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer codeProduct) {
        productRepository.deleteById(codeProduct);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully");
    }

}
