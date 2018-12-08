package com.pasha.figureRecognition.logic;

import java.util.List;

/**
 * This interface must be used to provide initial values for weights
 * before neural network training.
 */
@FunctionalInterface
public interface WeightsInitializer {

    /**
     * Returns initial values of weights.
     *
     * @param numberOfWeights number of weights to generate
     * @return generated weights
     */
    List<Double> initialize(int numberOfWeights);
}
