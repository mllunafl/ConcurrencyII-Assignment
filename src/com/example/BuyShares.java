package com.example;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.concurrent.Callable;

/**
 * Created by LunaFlores on 12/21/16.
 */
public class BuyShares implements Callable {
    private TraderService traderService;
    private String traderName;
    private Stock stock;

    public BuyShares(TraderService traderService, String traderName, Stock stock) {
        this.traderService = traderService;
        this.traderName = traderName;
        this.stock = stock;
    }

    @Override
    public String call() {
        System.out.println("B");
        //System.out.println("Calling buyShares method");
        try {
           String stockName = stock.getName();
           int numShares = stock.getShares();
           int price = stock.getPrice();
            traderService.buyShares(traderName, stockName, numShares, price);
            return "Trader " + traderName + " Bought " + numShares + " of stock " +stockName + " at " + price + " price ";
        } catch (SharesException e) {
            return e.getMessage();
        }
        //System.out.println("Done calling buyShares method")
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
