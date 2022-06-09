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
    public void addNewProduct(@RequestBody String title, @RequestBody Integer price) {
        Long id = productRepository.getProdRep().stream().max(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Long.compare(o1.getId(), o2.getId());
            }
        }).get().getId() + 1;
        productRepository.addProduct(new Product(id, title, price));
    }


}
