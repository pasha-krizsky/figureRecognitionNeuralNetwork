package com.pasha.figureRecognition.logic.function;

public class SigmoidalActivationFunction implements ActivationFunction {

    @Override
    public double calculate(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    @Override
    public double derivative(double x) {
        double sigmoid = calculate(x);
        return sigmoid * (1 - sigmoid);
    }
}
