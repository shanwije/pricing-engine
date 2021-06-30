package com.shanwije.pricingengine;

import com.shanwije.pricingengine.models.Product;
import com.shanwije.pricingengine.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.math.BigDecimal;

@SpringBootApplication()
public class PricingEngineApplication {

    final ProductRepository productRepository;

    public PricingEngineApplication(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PricingEngineApplication.class, args);
    }


    /*
        The reason to write the below is,
        since we don't any other way to populate database
        ( nor POST or PUT endpoints )
    */
    @EventListener(ApplicationReadyEvent.class)
    public void runAtStart() {
        var product1 = new Product();
        product1.setName("Penguin-ears");
        product1.setUnitsPerCarton(20);
        product1.setCartonPrice(BigDecimal.valueOf(175));
        this.productRepository.save(product1);

        var product2 = new Product();
        product2.setName("Horseshoe");
        product2.setUnitsPerCarton(5);
        product2.setCartonPrice(BigDecimal.valueOf(825));
        this.productRepository.save(product2);
    }

}
