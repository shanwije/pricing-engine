package com.shanwije.pricingengine.util.web;

import com.shanwije.pricingengine.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceCalculationRequest {
    private Product product;
    private double count;
    private boolean isCartons;
}
