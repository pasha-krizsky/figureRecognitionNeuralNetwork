package com.pasha.figureRecognition.logic;

import com.pasha.figureRecognition.model.NeuralNetwork;

/**
 * This facade works with {@link NeuralNetworkTeacher} implementations and aims to simplify
 * process of neural network training.
 */
public interface NeuralNetworkTeacherFacade {

    /**
     * Performs neural network training by using methods of {@link NeuralNetworkTeacher}
     */
    void train();

    /**
     * Returns error of neural network after training
     *
     * @return error of neural network or null if called before training
     */
    double getNeuralNetworkErrorAfterTraining();

    /**
     * Returns neural network
     *
     * @return neural network
     */
    NeuralNetwork getNeuralNetwork();

    /**
     * @return NeuralNetworkTeacher which are used by this facade
     */
    NeuralNetworkTeacher getNeuralNetworkTeacher();
}
