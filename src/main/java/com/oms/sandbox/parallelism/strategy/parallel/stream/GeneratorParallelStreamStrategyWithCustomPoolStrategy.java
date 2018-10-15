package com.oms.sandbox.parallelism.strategy.parallel.stream;

import com.oms.sandbox.parallelism.model.Generator;
import com.oms.sandbox.parallelism.strategy.GeneratorStrategy;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

public class GeneratorParallelStreamStrategyWithCustomPoolStrategy extends GeneratorStrategy {

    @Override
    public void displayFuelLevelsAndTheirAverage(List<Generator> generators) {
        out.println("Using GeneratorParallelStreamStrategy:");

        long start = currentTimeMillis();

        ForkJoinPool generatorThreadPool = new ForkJoinPool(10);

        List<Double> currentFuelLevels = null;
        try {
            currentFuelLevels = generatorThreadPool.submit(() -> generators.parallelStream().map(Generator::getCurrentFuelLevel).collect(toList())).get();
        } catch (final InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        displayResults(generators.size(), currentFuelLevels, (currentTimeMillis() - start));
    }
}