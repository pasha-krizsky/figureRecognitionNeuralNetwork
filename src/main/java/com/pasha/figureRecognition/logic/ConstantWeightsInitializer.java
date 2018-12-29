package com.pasha.figureRecognition.logic;

import java.util.ArrayList;
import java.util.List;

public class ConstantWeightsInitializer implements WeightsInitializer {

    private static final double CONSTANT = 0.0D;

    @Override
    public List<Double> initialize(int numberOfWeights) {
        List<Double> weights = new ArrayList<>(numberOfWeights);

        for (int i = 0; i < numberOfWeights; ++i) {
            weights.add(CONSTANT);
        }

        return weights;
    }
}
