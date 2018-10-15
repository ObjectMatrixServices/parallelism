package com.oms.sandbox.parallelism.model;

import static java.lang.System.*;
import static java.lang.Thread.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.lang.Math.*;


public class Generator {

    private final int dummyProcessingTimeInSeconds;

    public Generator(int dummyDelayInSeconds) {
        this.dummyProcessingTimeInSeconds = dummyDelayInSeconds;
    }
    public double getCurrentFuelLevel() {
        double currentFuelLevel;
        out.println("Thread Name: " + currentThread().getName());
        try {
            //Faking a delay to simulate the time taken to get the currentFuelLevel from an external source.
            SECONDS.sleep(dummyProcessingTimeInSeconds);

            //Randomly assigning some value to the current fuel level
            currentFuelLevel = round((random()*100)*100)/100.0;
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        return currentFuelLevel;
    }
}
