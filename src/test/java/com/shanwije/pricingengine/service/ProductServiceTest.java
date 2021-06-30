package com.shanwije.pricingengine.service;

import com.shanwije.pricingengine.models.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {

    static final Product p1 = new Product(1L, "Penguin-ears", 20, BigDecimal.valueOf(175));

    @Test
    void getPrice_whenNoCartons() {
        assertEquals(BigDecimal.valueOf(0.00).setScale(2, RoundingMode.CEILING),
                ProductServiceImpl.calculatePrice(0, p1.getUnitsPerCarton(), p1.getCartonPrice()));
        assertEquals(BigDecimal.valueOf(11.38).setScale(2, RoundingMode.CEILING),
                ProductServiceImpl.calculatePrice(1, p1.getUnitsPerCarton(), p1.getCartonPrice()));
    }

    @Test
    void getPrice_until3Cartons() {
        assertEquals(BigDecimal.valueOf(175.00).setScale(2, RoundingMode.CEILING),
                ProductServiceImpl.calculatePrice(20, p1.getUnitsPerCarton(), p1.getCartonPrice()));
        assertEquals(BigDecimal.valueOf(186.38).setScale(2, RoundingMode.CEILING),
                ProductServiceImpl.calculatePrice(21, p1.getUnitsPerCarton(), p1.getCartonPrice()));
    }

    @Test
    void getPrice_after3Cartons() {
        assertEquals(BigDecimal.valueOf(472.50).setScale(2, RoundingMode.CEILING),
                ProductServiceImpl.calculatePrice(60, p1.getUnitsPerCarton(), p1.getCartonPrice()));
        assertEquals(BigDecimal.valueOf(482.74).setScale(2, RoundingMode.CEILING),
                ProductServiceImpl.calculatePrice(61, p1.getUnitsPerCarton(), p1.getCartonPrice()));
    }
}