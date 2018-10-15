package com.oms.sandbox.parallelism.strategy.parallel.future;

import com.oms.sandbox.parallelism.model.Generator;
import com.oms.sandbox.parallelism.strategy.GeneratorStrategy;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.lang.System.*;
import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.CompletableFuture.*;
import static java.util.stream.Collectors.toList;

public class GeneratorCompleteableFuturesStrategy extends GeneratorStrategy {

    @Override
    public void displayFuelLevelsAndTheirAverage(List<Generator> generators) {
        out.println("Using GeneratorCompleteableFuturesStrategy:");
        long start = currentTimeMillis();

        List<CompletableFuture<Double>> futures = generators.stream().map(g -> supplyAsync(g::getCurrentFuelLevel))
                                                             .collect(toList());

        List<Double> currentFuelLevels = futures.stream().map(CompletableFuture::join).collect(toList());

        displayResults(generators.size(), currentFuelLevels, (currentTimeMillis() - start));
    }
}