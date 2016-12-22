package com.example;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    private static Random  random = new Random();

    private static List<String> stockNames = new ArrayList<String>();

    static {
        stockNames.add(new String("ABC"));
        stockNames.add(new String("DEF"));
        stockNames.add(new String("XYZ"));
    }

    private static List<String> traderNames = new ArrayList<String>();

    static {
        traderNames.add(new String("John"));
        traderNames.add(new String("Jake"));
        traderNames.add(new String("Jessica"));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(stockNames);
        System.out.println(traderNames);

        TraderService traderService = new TraderServiceImpl();

        List<Callable> callables = new ArrayList<Callable>();
        for (int i = 0; i < 100; i++){
            Stock stock = new Stock(stockNames.get(random.nextInt(stockNames.size())), random.nextInt(100), random.nextInt(100));
            callables.add(new AddShares(traderService, stock));

            callables.add(new GetStock(traderService, stockNames.get(random.nextInt(stockNames.size()))));

            String traderName = traderNames.get(random.nextInt(traderNames.size()));
            Stock stock2 = new Stock(stockNames.get(random.nextInt(stockNames.size())), random.nextInt(100), random.nextInt(100));
            callables.add(new BuyShares(traderService, traderName, stock2));
        }
        List<Future> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (Callable callable : callables){
            Future future = executorService.submit(callable);
            futures.add(future);
        }

        for (Future future : futures){
            System.out.println(future.get());
        }
        executorService.shutdown();

        System.out.println(traderService.getTrades().size());
    }
}

/*
      public static void main (String args[]) {

        Impl impl = new Impl();

        List<Callable> callables = new ArrayList<Callable>();

        callables.add(new AddShares(impl));
        callables.add(new BuyShares(impl));

        List<Future> futures = new ArrayList<Future>();

        for (callables) {
            futures.add(executorService.submit(callable from callables);
        }

    }
}
 */
