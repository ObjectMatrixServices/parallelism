package com.oms.sandbox.parallelism.strategy.parallel.stream;

import com.oms.sandbox.parallelism.model.Generator;
import com.oms.sandbox.parallelism.strategy.GeneratorStrategy;

import java.util.List;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

public class GeneratorParallelStreamStrategy extends GeneratorStrategy {

    @Override
    public void displayFuelLevelsAndTheirAverage(List<Generator> generators) {
        out.println("Using GeneratorParallelStreamStrategy:");

        long start = currentTimeMillis();

        List<Double> currentFuelLevels = generators.parallelStream().map(Generator::getCurrentFuelLevel).collect(toList());

        displayResults(generators.size(), currentFuelLevels, (currentTimeMillis() - start));
    }
}
