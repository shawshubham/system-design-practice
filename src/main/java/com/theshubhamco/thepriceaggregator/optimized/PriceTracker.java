package com.theshubhamco.thepriceaggregator.optimized;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PriceTracker {

    private final List<Double> prices;
    private final List<BigDecimal> prefixPriceSums;

    public PriceTracker() {
        this.prices = new ArrayList<>();
        this.prefixPriceSums = new ArrayList<>();
        this.prefixPriceSums.add(BigDecimal.ZERO);
    }

    public void addPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price must be greater than or equal to 0");
        }

        prices.add(price);
        prefixPriceSums.add(prefixPriceSums
                .get(prefixPriceSums.size() - 1)
                .add(BigDecimal.valueOf(price)));
    }

    public double getMovingAverage(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be greater than 0");
        }

        if (prices.isEmpty()) {
            throw new IllegalStateException("No prices available");
        }

        int count = Math.min(k, prices.size());
        return calculateMovingAverage(count);
    }

    private double calculateMovingAverage(int count) {
        BigDecimal sum;
        int lastIndex = prefixPriceSums.size() - 1;
        sum = prefixPriceSums.get(lastIndex).subtract(prefixPriceSums.get(lastIndex - count));

        System.out.println(sum);
        return sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}