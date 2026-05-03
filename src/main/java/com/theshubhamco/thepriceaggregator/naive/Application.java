package com.theshubhamco.thepriceaggregator.naive;

public class Application {
    public static void main(String[] args) {
        PriceTracker priceTracker = new PriceTracker();
        priceTracker.addPrice(100.0);
        priceTracker.addPrice(101.0);
        priceTracker.addPrice(102.0);
        priceTracker.addPrice(103.0);
        priceTracker.addPrice(104.0);
        priceTracker.addPrice(105.0);
        priceTracker.addPrice(106.0);
        priceTracker.addPrice(107.0);
        priceTracker.addPrice(108.0);
        priceTracker.addPrice(109.0);
        System.out.println(priceTracker.getMovingAverage(15));
    }
}
