package com.ads.data;

import com.ads.model.Product;
import com.ads.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final ProductRepository productRepository;

    @PostConstruct
    private void loadData() {
        if(productRepository.count() > 0) return;
        List<String> categoriesList = List.of("1","2","3");
        List<Product> products = new ArrayList<>();

        for(int i=0; i<5;++i) {
            int randomCategory = (int) (Math.random() * 3);
            Product p = new Product("title" + i, categoriesList.get(randomCategory), (randomCategory+1)*10 );
            products.add(p);
        }

        productRepository.saveAll(products);

    }
}
