package com.pasha.figureRecognition.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomWeightsInitializer implements WeightsInitializer {

    private static final int min = 0;
    private static final int max = 1;

    @Override
    public List<Double> initialize(int numberOfWeights) {
        List<Double> weights = new ArrayList<>(numberOfWeights);

        for (int i = 0; i < numberOfWeights; ++i) {
            weights.add(ThreadLocalRandom.current().nextDouble(min, max));
        }

        return weights;
    }
}
