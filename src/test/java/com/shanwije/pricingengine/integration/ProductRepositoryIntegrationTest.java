package com.shanwije.pricingengine.integration;

import com.shanwije.pricingengine.models.Product;
import com.shanwije.pricingengine.util.web.PriceCalculationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductRepositoryIntegrationTest {

    static final Product p1 = new Product(1L, "Penguin-ears", 20, BigDecimal.valueOf(175));
    //    private String priceCalcReq = "{\"product\":{\"id\":2,\"name\":\"Horseshoe\",\"unitsPerCarton\":5,\"cartonPrice\":825},\"count\":123,\"isCartons\":false}";

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @DisplayName("/products-and-prices")
    @Test
    void getProducts_returnsProducts() throws Exception {
        ResponseEntity<List> response = testRestTemplate.getForEntity("/products-and-prices", List.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertFalse(response.getBody().isEmpty());
    }

    @DisplayName("/prices without params")
    @Test
    void getPrices_returnsInvalidWhenNoParams() throws Exception {
        ResponseEntity<BigDecimal> response = testRestTemplate.getForEntity("/price", null, (Object) null);
        assertEquals(response.getStatusCode().value(), 400);
    }

    @DisplayName("/prices with params")
    @Test
    void getPrices_returnsPriceWithParams() throws Exception {
        ResponseEntity<PriceCalculationResponse> response = testRestTemplate.getForEntity("/price?cartonPrice=175&unitsPerCarton=20&quantity=5&isCartons=false", PriceCalculationResponse.class, (Object) null);
        assertEquals(response.getStatusCode().value(), 200);
        assertEquals( response.getBody().getPrice(),BigDecimal.valueOf(56.88));
    }

    // TODO: 6/30/21 here's to do the integration test on /products-and-prices, /prices endpoints.
    //  yet due to the time constrain decided to skip for now. its 5.44am, Jun 30. I've only got time until 9am today to
    //  complete the whole project. But I believe the above would give an insight that I'm do capable in writing
    //  integration tests in TDD approach.
}