package com.oms.sandbox.parallelism.client;

import com.oms.sandbox.parallelism.model.Generator;
import com.oms.sandbox.parallelism.strategy.GeneratorServiceContext;
import com.oms.sandbox.parallelism.strategy.parallel.future.GeneratorCompletableFuturesWithCustomExecutorStrategy;
import com.oms.sandbox.parallelism.strategy.parallel.future.GeneratorCompleteableFuturesStrategy;
import com.oms.sandbox.parallelism.strategy.parallel.stream.GeneratorParallelStreamStrategy;
import com.oms.sandbox.parallelism.strategy.parallel.stream.GeneratorParallelStreamStrategyWithCustomPoolStrategy;
import com.oms.sandbox.parallelism.strategy.sequential.GeneratorSequentialForLoopStrategy;
import com.oms.sandbox.parallelism.strategy.sequential.GeneratorSequentialStreamStrategy;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.System.*;
import static java.util.stream.Collectors.toList;

public class GeneratorServiceClient {

    private static List<Generator> getGenerators() {
        return IntStream.range(0, 10).mapToObj(i -> new Generator(1)).collect(toList());
    }

    public static void main(String[] args) {
        displayMenu();

        Scanner scanner = new Scanner(in);

        out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        while(!choice.equals("x")) {
            GeneratorServiceContext context = new GeneratorServiceContext();
            switch (choice) {
                case "1": {
                    context.setGeneratorStrategy(new GeneratorSequentialForLoopStrategy());
                    break;
                }
                case "2": {
                    context.setGeneratorStrategy(new GeneratorSequentialStreamStrategy());
                    break;
                }
                case "3": {
                    context.setGeneratorStrategy(new GeneratorParallelStreamStrategy());
                    break;
                }
                case "4": {
                    context.setGeneratorStrategy(new GeneratorParallelStreamStrategyWithCustomPoolStrategy());
                    break;
                }
                case "5": {
                    context.setGeneratorStrategy(new GeneratorCompleteableFuturesStrategy());
                    break;
                }
                case "6": {
                    context.setGeneratorStrategy(new GeneratorCompletableFuturesWithCustomExecutorStrategy());
                    break;
                }
                default:{
                    context.setGeneratorStrategy(null);
                    displayMenu();
                    break;
                }
            }
            context.displayGeneratorCurrentFuelLevelsAndTheirAverage(getGenerators());
            choice = scanner.nextLine();
        }
    }

    private static void displayMenu() {
        out.println("Use the following menu to indicate the strategy to Display Generators' Current Fuel Levels and their average.");
        out.println("1 - Use Sequential \"For\" Loop (1.7 Style)");
        out.println("2 - Use Sequential Streams");
        out.println("3 - Use Parallel Stream");
        out.println("4 - Use Parallel Stream With Custom Pool");
        out.println("5 - Use Multi Threads");
        out.println("6 - Use Multi Threads With Custom Executor");
        out.println("x - Exit");
    }
}
