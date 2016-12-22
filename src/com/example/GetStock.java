package com.example;

import java.util.concurrent.Callable;

/**
 * Created by LunaFlores on 12/21/16.
 */
public class GetStock implements Callable {
    private TraderService traderService;
    private String stockName;

    public GetStock(TraderService traderService, String stockName) {
        this.traderService = traderService;
        this.stockName = stockName;
    }

    @Override
    public String call() {
        System.out.println("G");
        //System.out.println("Calling getStock")
        Stock stock;
         stock =  traderService.getStock(stockName);
        //System.out.println("Done calling getStock " + stock);
        return "Get " + stockName + stock;
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