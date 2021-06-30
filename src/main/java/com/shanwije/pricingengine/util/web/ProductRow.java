package com.shanwije.pricingengine.util.web;

import com.shanwije.pricingengine.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductRow {
    private Product product;
    private List<BigDecimal> pricesList;
}
