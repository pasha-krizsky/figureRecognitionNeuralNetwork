package com.pasha.figureRecognition.logic;

import com.pasha.figureRecognition.model.NeuralNetwork;
import org.junit.Before;
import org.junit.Test;

public class DefaultNeuralNetworkTeacherFacadeTest {

    private NeuralNetworkTeacherFacade networkTeacherFacade;

    @Before
    public void setUp() {
        networkTeacherFacade = new DefaultNeuralNetworkTeacherFacade();
    }

    @Test
    public void testTrain() {
        long startTime = System.currentTimeMillis();
        networkTeacherFacade.train();
        long endTime = System.currentTimeMillis();
        System.out.println("Error: " + networkTeacherFacade.getNeuralNetworkErrorAfterTraining());
        System.out.println("Time: " + (endTime - startTime));
        NeuralNetwork neuralNetwork = networkTeacherFacade.getNeuralNetwork();
        System.out.println("Weights: " + neuralNetwork.getWeights());
        System.out.println();

        NeuralNetworkTeacher neuralNetworkTeacher = networkTeacherFacade.getNeuralNetworkTeacher();
        neuralNetworkTeacher.setInputs(getInputsForCircle()[0]);
        long startForwardTime = System.currentTimeMillis();
        neuralNetworkTeacher.forward();
        long endForwardTime = System.currentTimeMillis();
        System.out.println("Time of forward: " + (endForwardTime - startForwardTime));
        System.out.println("Outputs for circle: ");
        for (Double output : neuralNetwork.getOutputs()) {
            System.out.print(output + " ");
        }
        System.out.println();

        neuralNetworkTeacher.setInputs(getInputsForSquare()[1]);
        neuralNetworkTeacher.forward();
        System.out.println("Outputs for square: ");
        for (Double output : neuralNetwork.getOutputs()) {
            System.out.print(output + " ");
        }
        System.out.println();

        neuralNetworkTeacher.setInputs(getInputsForTriangle()[0]);
        neuralNetworkTeacher.forward();
        System.out.println("Outputs for triangle: ");
        for (Double output : neuralNetwork.getOutputs()) {
            System.out.print(output + " ");
        }
        System.out.println();

    }

    private Double[][] getInputsForCircle() {
        final String path = "D:\\projects\\java\\figureRecognition\\src\\test\\resources\\circle";
        TrainingSampleProvider provider = new TrainingSampleFromImageProvider(path);
        return provider.provide();
    }

    private Double[][] getInputsForSquare() {
        final String path = "D:\\projects\\java\\figureRecognition\\src\\test\\resources\\square";
        TrainingSampleProvider provider = new TrainingSampleFromImageProvider(path);
        return provider.provide();
    }

    private Double[][] getInputsForTriangle() {
        final String path = "D:\\projects\\java\\figureRecognition\\src\\test\\resources\\triangle";
        TrainingSampleProvider provider = new TrainingSampleFromImageProvider(path);
        return provider.provide();
    }
}