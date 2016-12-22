package com.example;

import java.util.concurrent.Callable;

/**
 * Created by LunaFlores on 12/21/16.
 */
public class AddShares implements Callable<String> {
    private TraderService traderService;
    private Stock stock;

    public AddShares(TraderService traderService, Stock stock) {
        this.traderService = traderService;
        this.stock = stock;
    }

    @Override
    public String call() {
        System.out.println("A");
        //System.out.println("Calling addShares");
        String stockName = stock.getName();
        int numShares = stock.getShares();
        int price = stock.getPrice();
        traderService.addShares(stockName, numShares,price);
        //System.out.println("Done calling addShares");
        return "Added " + numShares + " at " + price + " for stock " +stockName ;
    }
}

/*
public class AddShares implements Callable<String> {

    // constructor does not change

    public String call() {
        System.out.print("A");
         ...
        return "Added " + numShares + " at " + price + " for stock " + stockName;

    }
 */