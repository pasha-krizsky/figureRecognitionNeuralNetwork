package com.pasha.figureRecognition.logic.function;

/**
 * Represents activation function of a neuron.
 */
public interface ActivationFunction {
    double calculate(double x);

    double derivative(double x);
}
