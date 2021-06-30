package com.shanwije.pricingengine.service;

import com.shanwije.pricingengine.repository.ProductRepository;
import com.shanwije.pricingengine.util.web.PriceCalculationRequest;
import com.shanwije.pricingengine.util.web.ProductRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.math.BigDecimal.valueOf;

@Service
public class ProductServiceImpl implements ProductService {

    private static final int MINIMUM_CARTON_COUNT_FOR_DISCOUNT = 3;
    private static final Double DISCOUNT = 0.90;
    private static final Double PRICE_INCREMENT_FOR_UNITS = 1.30;
    private static final int REQUIRED_PRICES_LIST_LENGTH = 50;

    final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    protected static BigDecimal calculatePrice(double amount, int unitsPerCarton, BigDecimal cartonPrice) {

        int cartons = (int) Math.floor(amount / unitsPerCarton);
        int units = (int) (amount % unitsPerCarton);
        if (cartons >= MINIMUM_CARTON_COUNT_FOR_DISCOUNT)
            cartonPrice = cartonPrice.multiply(valueOf(DISCOUNT));

        //         even though the amount exceeds 3 cartons, yet still the below applies for the individual units
        BigDecimal carTonPriceForUnits = cartonPrice.multiply(valueOf(PRICE_INCREMENT_FOR_UNITS));

        //         in the assignment it's not clear whether the single unit prices calculated
        //         using initial carton price or the discounted carton price.
        //         Decided to go with the discounted carton price
        return cartonPrice.multiply(valueOf(cartons))
                .add(carTonPriceForUnits.divide(valueOf(unitsPerCarton)).multiply(valueOf(units)))
                .setScale(2, RoundingMode.CEILING);
    }

    @Override
    public List<ProductRow> getProductsAndPrices() {
        return productRepository.findAll().stream().map(product -> {
            List<BigDecimal> priceList = IntStream.range(0, REQUIRED_PRICES_LIST_LENGTH).boxed()
                    .map(amount ->
                            calculatePrice(amount, product.getUnitsPerCarton(), product.getCartonPrice()))
                    .collect(Collectors.toList());
            return new ProductRow(product, priceList);
        }).collect(Collectors.toList());
    }

    @Override
    public BigDecimal getPrice(PriceCalculationRequest request) {
        var product = request.getProduct();
        var count = request.getCount();
        double totalUnits = request.isCartons()
                ? count * product.getUnitsPerCarton()
                : count;
        return calculatePrice(totalUnits, product.getUnitsPerCarton(), product.getCartonPrice());
    }
}
