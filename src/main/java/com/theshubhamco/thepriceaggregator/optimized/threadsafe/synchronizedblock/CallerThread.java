package com.theshubhamco.thepriceaggregator.optimized.threadsafe.synchronizedblock;

public class CallerThread implements Runnable {
    private final PriceTracker priceTracker;
    private final double price;
    private final int averageOfLastKPrices;

    public CallerThread(PriceTracker priceTracker, double price, int averageOfLastKPrices) {
        this.priceTracker = priceTracker;
        this.price = price;
        this.averageOfLastKPrices = averageOfLastKPrices;
    }

    @Override
    public void run() {
        this.priceTracker.addPrice(price);
        System.out.println("Price added: " + price);

        this.priceTracker.getMovingAverage(averageOfLastKPrices);
        System.out.println("Average: " + this.priceTracker.getMovingAverage(averageOfLastKPrices));
    }
}
