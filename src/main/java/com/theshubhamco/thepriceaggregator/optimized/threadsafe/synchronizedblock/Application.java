package com.theshubhamco.thepriceaggregator.optimized.threadsafe.synchronizedblock;

public class Application {
    public static void main(String[] args) {
        PriceTracker priceTracker = new PriceTracker();

        Thread t1 = new Thread(new CallerThread(priceTracker, 100.0, 4));
        Thread t2 = new Thread(new CallerThread(priceTracker, 101.0, 3));
        Thread t3 = new Thread(new CallerThread(priceTracker, 102.0, 3));
        Thread t4 = new Thread(new CallerThread(priceTracker, 103.0, 4));
        Thread t5 = new Thread(new CallerThread(priceTracker, 104.0, 4));
        Thread t6 = new Thread(new CallerThread(priceTracker, 105.0, 5));
        Thread t7 = new Thread(new CallerThread(priceTracker, 106.0, 4));
        Thread t8 = new Thread(new CallerThread(priceTracker, 107.0,3));
        Thread t9 = new Thread(new CallerThread(priceTracker, 108.0, 15));
        Thread t10 = new Thread(new CallerThread(priceTracker, 109.0,6));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
    }
}
