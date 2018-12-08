package com.pasha.figureRecognition.logic;

import com.pasha.figureRecognition.logic.function.ActivationFunction;

/**
 * Performs training of neural network.
 */
public interface NeuralNetworkTeacher {

    /**
     * Initializes weights of neural network.
     * It is recommended to use {@link WeightsInitializer} implementations.
     */
    void initWeightsForAllNeurons();

    /**
     * Initializes values of neurons in input layer. This method must be called
     * for each input provided by {@link TrainingSampleProvider} one time during one epoch.
     * <p>
     * This method should be used in pair with {@link NeuralNetworkTeacher#setExpectedOutputs(Double...)}
     *
     * @param inputs input values of neural network.
     */
    void setInputs(Double... inputs);

    /**
     * Expected outputs for concrete inputs.
     * This method should be used in pair with {@link NeuralNetworkTeacher#setInputs(Double...)}
     *
     * @param outputs expected output values of neural network
     */
    void setExpectedOutputs(Double... outputs);

    /**
     * Uses input values of neural network and its weights to calculate values of
     * other neurons of neural network.
     * <p>
     * This method should use {@link ActivationFunction} implementations to calculate the values of neurons.
     */
    void forward();

    /**
     * Propagates error from output layer of neural network to other layers from right to left.
     * <p>
     * This method should use {@link ActivationFunction} implementations to calculate derivatives.
     */
    void backPropagate();
}
