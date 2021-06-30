package com.shanwije.pricingengine.service;

import com.shanwije.pricingengine.util.web.PriceCalculationRequest;
import com.shanwije.pricingengine.util.web.ProductRow;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductRow> getProductsAndPrices();
    BigDecimal getPrice(PriceCalculationRequest request);
}
