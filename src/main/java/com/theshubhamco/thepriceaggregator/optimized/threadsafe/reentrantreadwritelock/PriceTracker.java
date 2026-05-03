package com.theshubhamco.thepriceaggregator.optimized.threadsafe.reentrantreadwritelock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PriceTracker {

    private final List<Double> prices;
    private final List<BigDecimal> prefixPriceSums;
    private final ReentrantReadWriteLock lock;

    public PriceTracker() {
        this.prices = new ArrayList<>();
        this.prefixPriceSums = new ArrayList<>();
        this.prefixPriceSums.add(BigDecimal.ZERO);
        this.lock = new ReentrantReadWriteLock();
    }

    public void addPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price must be greater than or equal to 0");
        }

        lock.writeLock().lock();
        try {
            prices.add(price);

            BigDecimal latestPrefixSum = prefixPriceSums
                    .get(prefixPriceSums.size() - 1)
                    .add(BigDecimal.valueOf(price));

            prefixPriceSums.add(latestPrefixSum);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public double getMovingAverage(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be greater than 0");
        }

        lock.readLock().lock();
         try {
            if (prices.isEmpty()) {
                throw new IllegalStateException("No prices available");
            }

            int count = Math.min(k, prices.size());
            return calculateMovingAverage(count);
        } finally {
            lock.readLock().unlock();
        }
    }

    private double calculateMovingAverage(int count) {
        BigDecimal sum;
        int lastIndex = prefixPriceSums.size() - 1;
        sum = prefixPriceSums.get(lastIndex).subtract(prefixPriceSums.get(lastIndex - count));

        return sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}