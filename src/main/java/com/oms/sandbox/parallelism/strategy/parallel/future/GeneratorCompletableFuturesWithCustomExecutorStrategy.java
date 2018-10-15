package com.oms.sandbox.parallelism.strategy.parallel.future;

import com.oms.sandbox.parallelism.model.Generator;
import com.oms.sandbox.parallelism.strategy.GeneratorStrategy;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toList;

public class GeneratorCompletableFuturesWithCustomExecutorStrategy extends GeneratorStrategy {

    @Override
    public void displayFuelLevelsAndTheirAverage(List<Generator> generators) {
        out.println("Using GeneratorCompletableFuturesWithCustomExecutorStrategy:");
        long start = currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(3);
//        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Double> currentFuelLevels = generators.stream()
                                                   .map(g -> supplyAsync(g::getCurrentFuelLevel, executor))
                                                   .collect(toList()).stream()
                                                                     .map(CompletableFuture::join)
                                                                     .collect(toList());

        displayResults(generators.size(), currentFuelLevels, (currentTimeMillis() - start));
        executor.shutdown();
        if(!executor.isShutdown()){
            try {
                executor.awaitTermination(1, SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                executor.shutdownNow();
            }
        }
    }
}
