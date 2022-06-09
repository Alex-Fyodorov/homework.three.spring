package ru.geekbrains.homework.three.spring.hw;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> prodRep;

    @PostConstruct
    public void init() {
        this.prodRep = new ArrayList<>(List.of(
                new Product(1L, "Milk", 89),
                new Product(2L, "Bread", 39),
                new Product(3L, "Cheese", 149)
        ));
    }

    public List<Product> getProdRep(){
        return Collections.unmodifiableList(prodRep);
    }

    public void addProduct(Product product) {
        prodRep.add(product);
    }

    public void removeProduct(Product product){
        prodRep.remove(product);
    }

    public void removeAll(){
        prodRep.clear();
    }
}
