package com.pasha.figureRecognition.logic;

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
}
