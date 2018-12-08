package com.pasha.figureRecognition.model;

import java.util.List;

/**
 * This class represents a neuron.
 */
public class Neuron {

    /**
     * A calculated value of a neuron.
     * <p>
     * Calculation of this value is based on applying activation function to the scalar product
     * of corresponding values of neurons from previous layer and their weights.
     * <p>
     * The value might be assigned without calculation if we are dealing with input layer.
     * <p>
     * For output layer this value is an output (answer) of a neural network.
     */
    private Double value;

    /**
     * Scalar product of corresponding values of neurons from previous layer and their weights.
     * <p>
     * The value might be assigned without calculation if we are dealing with input layer.
     */
    private Double inputValue;

    /**
     * delta = activationFunction(x)' * error
     * <p>
     * 1. For output layer the error is just a difference between expected output and actual output.
     * <p>
     * 2. For others the error is a scalar product of weights and corresponding deltas of neurons from next layer.
     */
    private Double delta;

    /**
     * Output weights of neuron.
     * For output layer they are null.
     */
    private List<Double> weights;

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getInputValue() {
        return inputValue;
    }

    public void setInputValue(Double inputValue) {
        this.inputValue = inputValue;
    }

    public Double getDelta() {
        return delta;
    }

    public void setDelta(Double delta) {
        this.delta = delta;
    }
}
