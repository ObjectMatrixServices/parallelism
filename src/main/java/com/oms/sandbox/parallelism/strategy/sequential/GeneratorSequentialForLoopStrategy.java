package com.oms.sandbox.parallelism.strategy.sequential;

import com.oms.sandbox.parallelism.model.Generator;
import com.oms.sandbox.parallelism.strategy.GeneratorStrategy;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

public class GeneratorSequentialForLoopStrategy extends GeneratorStrategy {

    @Override
    public void displayFuelLevelsAndTheirAverage(List<Generator> generators) {
        out.println("Using GeneratorSequentialForLoopStrategy:");

        long start = currentTimeMillis();

        List<Double> currentFuelLevels = new ArrayList<>();
        for (Generator generator : generators) {
            currentFuelLevels.add(generator.getCurrentFuelLevel());
        }

        displayResults(generators.size(), currentFuelLevels, (currentTimeMillis() - start));
    }
}