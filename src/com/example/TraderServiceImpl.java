package com.example;

import sun.plugin2.message.Message;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by LunaFlores on 12/21/16.
 */
public class TraderServiceImpl implements TraderService {
    private Map<String, Stock> stocks = new HashMap<>();
    private List<Trade> trades = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void addShares(String stockName, int numShares, int price) {
        try {
            lock.lock();
            Stock stock = stocks.get(stockName);
            if (stock == null) {
                Stock stock2 = new Stock(stockName, numShares, price);
                stocks.put(stockName, stock2);
            } else {
                int newShares = stock.getShares() + numShares;
                Stock stock2 = new Stock(stockName, newShares, price);
                stocks.put(stockName, stock2);
            }
        } finally {
            lock.unlock();
        }


    }

    @Override
    public Stock getStock(String stockName) {
        try {
            lock.lock();
            Stock stock = stocks.get(stockName);
            return stock;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Trade buyShares(String traderName, String sockName, int numShares, int price) throws SharesException {
        try {
            lock.lock();
            Stock stock = stocks.get(sockName);
            if (stock == null) {
                throw new SharesException();
            }
            int newShares = stock.getShares() - numShares;
            if (stock.getShares() > numShares) {
                Stock stock2 = new Stock(sockName, price, newShares);
                stocks.put(sockName, stock2);
            } else throw new SharesException();
        } finally {
            lock.unlock();
        }
        Trade trade = new Trade(sockName, price, numShares, traderName, LocalDate.now());
        trades.add(trade);
        return trade;
    }

    @Override
    public void clearStocks() {
        stocks.clear();
    }

    @Override
    public List<Trade> getTrades() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return trades;
    }
}
