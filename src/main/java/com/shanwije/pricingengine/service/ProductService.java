package com.shanwije.pricingengine.service;

import com.shanwije.pricingengine.util.web.PriceCalculationRequest;
import com.shanwije.pricingengine.util.web.PriceCalculationResponse;
import com.shanwije.pricingengine.util.web.ProductRow;

import java.util.List;

public interface ProductService {
    List<ProductRow> getProductsAndPrices();

    PriceCalculationResponse getPrice(PriceCalculationRequest request);
}
