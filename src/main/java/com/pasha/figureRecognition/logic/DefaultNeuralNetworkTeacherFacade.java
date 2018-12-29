package com.pasha.figureRecognition.logic;

import com.pasha.figureRecognition.logic.function.ActivationFunction;
import com.pasha.figureRecognition.logic.function.SigmoidalActivationFunction;
import com.pasha.figureRecognition.model.NeuralNetwork;

public class DefaultNeuralNetworkTeacherFacade implements NeuralNetworkTeacherFacade {
    private static final String PATH_TO_IMAGES = "D:\\projects\\java\\figureRecognition\\src\\main\\resources\\all_samples";

    private static final double LEARNING_RATE = 0.1;
    private static final int NUMBER_OF_EPOCHS = 10_000;

    private static final int NUM_NEURONS_IN_INPUT_LAYER = 49;
    private static final int NUM_NEURONS_IN_HIDDEN_LAYER = 4;
    private static final int NUM_NEURONS_IN_OUTPUT_LAYER = 3;
    private static final int NUM_HIDDEN_LAYERS = 1;

    private NeuralNetwork neuralNetwork;
    private NeuralNetworkTeacher networkTeacher;
    private TrainingSampleProvider trainingSampleProvider;

    private Double[][] expectedOutputs;
    private Double neuralNetworkErrorAfterCalculation;

    public DefaultNeuralNetworkTeacherFacade() {
        ActivationFunction activationFunction = new SigmoidalActivationFunction();
        WeightsInitializer weightsInitializer = new RandomWeightsInitializer();

        trainingSampleProvider = new TrainingSampleFromImageProvider(PATH_TO_IMAGES);

        // set the outputs in the correct order!
        Double[] circleOutput = {1D, 0D, 0D};
        Double[] squareOutput = {0D, 1D, 0D};
        Double[] triangleOutput = {0D, 0D, 1D};
        expectedOutputs = new Double[][]{
                // circle
                circleOutput, circleOutput, circleOutput, circleOutput, circleOutput,
                circleOutput, circleOutput, circleOutput, circleOutput, circleOutput, circleOutput,
                // square
                squareOutput, squareOutput, squareOutput, squareOutput, squareOutput,
                squareOutput, squareOutput, squareOutput, squareOutput, squareOutput, squareOutput,
                // triangle
                triangleOutput, triangleOutput, triangleOutput, triangleOutput, triangleOutput,
                triangleOutput, triangleOutput, triangleOutput, triangleOutput, triangleOutput, triangleOutput
        };

        neuralNetwork = buildEmptyNeuralNetwork();
        networkTeacher = buildNetworkTeacher(neuralNetwork, activationFunction, weightsInitializer);
    }

    @Override
    public void train() {
        networkTeacher.initWeightsForAllNeurons();
        Double[][] inputs = trainingSampleProvider.provide();
        for (int epochIndex = 0; epochIndex < NUMBER_OF_EPOCHS; ++epochIndex) {
            double error = 0.0;
            for (int inputIndex = 0; inputIndex < inputs.length; ++inputIndex) {
                networkTeacher.setInputs(inputs[inputIndex]);
                networkTeacher.setExpectedOutputs(expectedOutputs[inputIndex]);
                networkTeacher.forward();
                networkTeacher.backPropagate();
                error += mse(expectedOutputs[inputIndex], neuralNetwork.getOutputs());
            }
            neuralNetworkErrorAfterCalculation = error / inputs.length;
        }
    }

    @Override
    public double getNeuralNetworkErrorAfterTraining() {
        return neuralNetworkErrorAfterCalculation;
    }

    @Override
    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    @Override
    public NeuralNetworkTeacher getNeuralNetworkTeacher() {
        return networkTeacher;
    }

    private NeuralNetwork buildEmptyNeuralNetwork() {
        return NeuralNetwork
                .builder()
                .numNeuronsInInputLayer(NUM_NEURONS_IN_INPUT_LAYER)
                .numNeuronsInHiddenLayer(NUM_NEURONS_IN_HIDDEN_LAYER)
                .numNeuronsInOutputLayer(NUM_NEURONS_IN_OUTPUT_LAYER)
                .numHiddenLayers(NUM_HIDDEN_LAYERS)
                .build();
    }

    private DefaultNetworkTeacher buildNetworkTeacher(
            NeuralNetwork neuralNetwork,
            ActivationFunction activationFunction,
            WeightsInitializer weightsInitializer) {
        return new DefaultNetworkTeacher(
                neuralNetwork,
                activationFunction,
                weightsInitializer,
                LEARNING_RATE);
    }

    /**
     * Calculates minimal square error.
     */
    private double mse(Double[] expected, Double[] actual) {
        int length = expected.length;
        double error = 0.0;
        for (int i = 0; i < length; ++i) {
            Double diff = expected[i] - actual[i];
            error += diff * diff;
        }
        return error / length;
    }
}
