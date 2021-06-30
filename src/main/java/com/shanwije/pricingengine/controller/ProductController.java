package com.shanwije.pricingengine.controller;

import com.shanwije.pricingengine.service.ProductService;
import com.shanwije.pricingengine.util.web.PriceCalculationRequest;
import com.shanwije.pricingengine.util.web.PriceCalculationResponse;
import com.shanwije.pricingengine.util.web.ProductRow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin
    @GetMapping("/products-and-prices")
    public ResponseEntity<List<ProductRow>> getProducts() {
        return new ResponseEntity<>(productService.getProductsAndPrices(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/price")
    public ResponseEntity<PriceCalculationResponse> getPrice(@RequestParam("quantity") double quantity,
                                                             @RequestParam("isCartons") boolean isCartons,
                                                             @RequestParam("unitsPerCarton") int unitsPerCarton,
                                                             @RequestParam("cartonPrice") BigDecimal cartonPrice) {
        return new ResponseEntity<>(productService
                .getPrice(new PriceCalculationRequest(unitsPerCarton, cartonPrice, quantity, isCartons)), HttpStatus.OK);
    }
}
