package ru.geekbrains.homework.three.spring.hw;

import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.getProdRep();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productRepository.getProdRep().stream().
                filter(product -> product.getId().equals(id)).
                findFirst().
                orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @PostMapping("/products")
    public void addNewProduct(@RequestBody Product product) {
        Long id = productRepository.getProdRep().stream().max(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Long.compare(o1.getId(), o2.getId());
            }
        }).get().getId() + 1;
        product.setId(id);
        productRepository.addProduct(product);
    }

    @DeleteMapping("/products")
    public void deleteAll(){
        productRepository.removeAll();
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable Long id){
        Product product = productRepository.getProdRep().
                stream().filter(p -> p.getId().equals(id)).
                findFirst().orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.removeProduct(product);
    }
}
