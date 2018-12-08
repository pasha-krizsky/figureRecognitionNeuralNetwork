package com.pasha.figureRecognition.model;

import org.junit.Assert;
import org.junit.Test;

public class NeuralNetworkCreationTest {

    /**
     * Testing creation of empty NN structure
     */
    @Test
    public void testCreateEmptyNeuralNetwork() {
        final int NUM_NEURONS_IN_INPUT_LAYER = 49;
        final int NUM_NEURONS_IN_HIDDEN_LAYER = 49;
        final int NUM_NEURONS_IN_OUTPUT_LAYER = 3;
        final int NUM_HIDDEN_LAYERS = 1;

        NeuralNetwork neuralNetwork = NeuralNetwork
                .builder()
                .numNeuronsInInputLayer(NUM_NEURONS_IN_INPUT_LAYER)
                .numNeuronsInHiddenLayer(NUM_NEURONS_IN_HIDDEN_LAYER)
                .numNeuronsInOutputLayer(NUM_NEURONS_IN_OUTPUT_LAYER)
                .numHiddenLayers(NUM_HIDDEN_LAYERS)
                .build();

        Assert.assertNotNull("Layers must not be null", neuralNetwork.getLayers());

        final int expectedLayersSize = NUM_HIDDEN_LAYERS + 2;
        final int actualLayersSize = neuralNetwork.getLayers().size();
        Assert.assertEquals("Incorrect number of layers", expectedLayersSize, actualLayersSize);

        Layer inputLayer = neuralNetwork.getLayers().get(0);

        checkNumberOfNeuronsInInputLayer(inputLayer.getNumNeurons());
        checkNumberOfNeuronsInInputLayer(inputLayer.getNeurons().size());
        checkNeuronsNotNull(neuralNetwork);
    }

    private void checkNumberOfNeuronsInInputLayer(int numberOfNeurons) {
        Assert.assertEquals(49, numberOfNeurons);
    }


    private void checkNeuronsNotNull(NeuralNetwork neuralNetwork) {
        for (Layer layer : neuralNetwork.getLayers()) {
            for (int i = 0; i < layer.getNeurons().size(); ++i) {
                Assert.assertNotNull(layer.getNeurons().get(i));
            }
        }
    }
}
