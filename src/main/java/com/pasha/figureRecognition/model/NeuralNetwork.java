package com.pasha.figureRecognition.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a neural network.
 */
public class NeuralNetwork {

    private List<Layer> layers;

    /**
     * Use {@link NeuralNetworkBuilder} for neural network creation.
     */
    private NeuralNetwork(List<Layer> layers) {
        this.layers = layers;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public List<List<List<Double>>> getWeights() {
        List<List<List<Double>>> weights = new ArrayList<>();
        for (Layer layer: getLayers()) {
            List<List<Double>> weightsForCurrentLayer = new ArrayList<>();
            weights.add(weightsForCurrentLayer);
            for (Neuron neuron: layer.getNeurons()) {
                weightsForCurrentLayer.add(neuron.getWeights());
            }
        }
        return weights;
    }

    public Double[] getOutputs() {
        int lastLayerSize = getLayers().size();
        List<Neuron> neuronsOfLastLayer = getLayers().get(lastLayerSize - 1).getNeurons();
        Double[] outputs = new Double[neuronsOfLastLayer.size()];
        for (int neuronIndex = 0; neuronIndex < neuronsOfLastLayer.size(); ++neuronIndex) {
            outputs[neuronIndex] = neuronsOfLastLayer.get(neuronIndex).getValue();
        }

        return outputs;
    }

    public static class NeuralNetworkBuilder {
        private int numNeuronsInInputLayer;
        private int numNeuronsInOutputLayer;
        private int numNeuronsInHiddenLayer;
        private int numHiddenLayers;

        private List<Layer> layers;

        public NeuralNetworkBuilder numNeuronsInInputLayer(int numNeuronsInInputLayer) {
            this.numNeuronsInInputLayer = numNeuronsInInputLayer;
            return this;
        }

        public NeuralNetworkBuilder numNeuronsInOutputLayer(int numNeuronsInOutputLayer) {
            this.numNeuronsInOutputLayer = numNeuronsInOutputLayer;
            return this;
        }

        public NeuralNetworkBuilder numNeuronsInHiddenLayer(int numNeuronsInHiddenLayer) {
            this.numNeuronsInHiddenLayer = numNeuronsInHiddenLayer;
            return this;
        }

        public NeuralNetworkBuilder numHiddenLayers(int numHiddenLayers) {
            this.numHiddenLayers = numHiddenLayers;
            return this;
        }

        /**
         * Creates neural network structure.
         */
        public NeuralNetwork build() {
            layers = new ArrayList<>(numHiddenLayers + 2);

            buildInputLayer();
            buildHiddenLayers();
            buildOutputLayer();

            return new NeuralNetwork(layers);
        }

        private void buildInputLayer() {
            layers.add(new Layer(numNeuronsInInputLayer));
        }

        private void buildHiddenLayers() {
            for (int i = 0; i < numHiddenLayers; ++i) {
                layers.add(new Layer(numNeuronsInHiddenLayer));
            }
        }

        private void buildOutputLayer() {
            layers.add(new Layer(numNeuronsInOutputLayer));
        }
    }

    public static NeuralNetworkBuilder builder() {
        return new NeuralNetworkBuilder();
    }
}
