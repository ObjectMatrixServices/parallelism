package com.oms.sandbox.parallelism.strategy.sequential;

import com.oms.sandbox.parallelism.model.Generator;
import com.oms.sandbox.parallelism.strategy.GeneratorStrategy;

import java.util.List;

import static java.lang.System.*;
import static java.util.stream.Collectors.toList;

public class GeneratorSequentialStreamStrategy extends GeneratorStrategy {

    @Override
    public void displayFuelLevelsAndTheirAverage(List<Generator> generators) {
        out.println("Using GeneratorSequentialStreamStrategy:");

        long start = currentTimeMillis();

        List<Double> currentFuelLevels = generators.stream().map(Generator::getCurrentFuelLevel).collect(toList());

        displayResults(generators.size(), currentFuelLevels, (currentTimeMillis() - start));
    }
}
