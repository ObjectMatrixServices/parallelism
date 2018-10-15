package com.oms.sandbox.parallelism.strategy;

import com.oms.sandbox.parallelism.model.Generator;

import java.util.List;

public abstract class GeneratorStrategy {

    public abstract void displayFuelLevelsAndTheirAverage(List<Generator> generators);

    protected void displayResults(int numberOfGenerators, List<Double> currentFuelLevels, long duration) {
        System.out.println("Current Fuel Levels: " + currentFuelLevels);
        System.out.println("Current Fuel Levels' Average: " + currentFuelLevels.stream().mapToDouble(d -> d).average().getAsDouble());
        System.out.printf("Serviced %d generators in %d millis\n", numberOfGenerators, duration);
        System.out.println("**************************************************************************************");
    }
}