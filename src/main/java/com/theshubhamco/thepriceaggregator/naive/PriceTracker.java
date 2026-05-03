package com.theshubhamco.thepriceaggregator.naive;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PriceTracker {

    private final List<Double> prices;

    public PriceTracker() {
        this.prices = new ArrayList<>();
    }

    public void addPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price must be greater than or equal to 0");
        }

        prices.add(price);
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
        BigDecimal sum = BigDecimal.ZERO;

        for (int index = prices.size() - count; index < prices.size(); index++) {
            sum = sum.add(BigDecimal.valueOf(prices.get(index)));
        }

        return sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}