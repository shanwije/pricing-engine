package com.shanwije.pricingengine.util.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceCalculationRequest {
    private int unitsPerCarton;
    private BigDecimal cartonPrice;
    private double count;
    private boolean isCartons;
}
