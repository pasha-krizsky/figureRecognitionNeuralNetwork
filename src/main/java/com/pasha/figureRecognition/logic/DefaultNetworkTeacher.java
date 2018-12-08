package com.pasha.figureRecognition.logic;

import com.pasha.figureRecognition.logic.function.ActivationFunction;
import com.pasha.figureRecognition.model.Layer;
import com.pasha.figureRecognition.model.NeuralNetwork;
import com.pasha.figureRecognition.model.Neuron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultNetworkTeacher implements NeuralNetworkTeacher {

    private NeuralNetwork neuralNetwork;
    private ActivationFunction activationFunction;
    private WeightsInitializer weightsInitializer;
    private Double learningRate;

    private List<Double> expectedOutputs;

    public DefaultNetworkTeacher(
            NeuralNetwork neuralNetwork,
            ActivationFunction activationFunction,
            WeightsInitializer weightsInitializer,
            Double learningRate) {
        this.neuralNetwork = neuralNetwork;
        this.activationFunction = activationFunction;
        this.weightsInitializer = weightsInitializer;
        this.learningRate = learningRate;
        expectedOutputs = new ArrayList<>();
    }

    @Override
    public void initWeightsForAllNeurons() {
        List<Layer> layers = neuralNetwork.getLayers();
        int numLayersWithoutOutputLayer = layers.size() - 1;
        for (int layerIndex = 0; layerIndex < numLayersWithoutOutputLayer; ++layerIndex) {
            Layer currentLayer = layers.get(layerIndex);
            int numNeuronsInCurrentLayer = currentLayer.getNumNeurons();
            Layer nextLayer = layers.get(layerIndex + 1);
            int numNeuronsInNextLayer = nextLayer.getNumNeurons();
            for (int neuronIndex = 0; neuronIndex < numNeuronsInCurrentLayer; ++neuronIndex) {
                Neuron neuron = currentLayer.getNeurons().get(neuronIndex);
                neuron.setWeights(weightsInitializer.initialize(numNeuronsInNextLayer));
            }
        }
    }

    @Override
    public void setInputs(Double... inputs) {
        Layer inputLayer = neuralNetwork.getLayers().get(0);
        List<Neuron> neurons = inputLayer.getNeurons();
        for (int i = 0; i < inputs.length; ++i) {
            Neuron neuron = neurons.get(i);
            neuron.setInputValue(inputs[i]);
            neuron.setValue(inputs[i]);
        }
    }

    @Override
    public void setExpectedOutputs(Double... outputs) {
        expectedOutputs = new ArrayList<>(Arrays.asList(outputs));
    }

    @Override
    public void forward() {
        int numOfLayers = neuralNetwork.getLayers().size();
        for (int layerIndex = 1; layerIndex < numOfLayers; ++layerIndex) {
            Layer layer = neuralNetwork.getLayers().get(layerIndex);

            int numOfNeurons = layer.getNumNeurons();
            for (int neuronIndex = 0; neuronIndex < numOfNeurons; ++neuronIndex) {
                Neuron neuron = layer.getNeurons().get(neuronIndex);
                neuron.setInputValue(calculateInputValueForNeuron(layerIndex, neuronIndex));
                neuron.setValue(activationFunction.calculate(neuron.getInputValue()));
            }
        }
    }

    @Override
    public void backPropagate() {
        calculateDeltasForOutputLayer();
        for (int layerIndex = neuralNetwork.getLayers().size() - 1; layerIndex > 0; --layerIndex) {
            Layer currentLayer = neuralNetwork.getLayers().get(layerIndex);
            Layer previousLayer = neuralNetwork.getLayers().get(layerIndex - 1);
            correctWeightsForPreviousLayer(currentLayer, previousLayer);
            calculateDeltasForPreviousLayer(currentLayer, previousLayer);
        }
    }

    private void calculateDeltasForOutputLayer() {
        int outputLayerIndex = neuralNetwork.getLayers().size() - 1;
        Layer outputLayer = neuralNetwork.getLayers().get(outputLayerIndex);

        for (int i = 0; i < outputLayer.getNumNeurons(); ++i) {
            Neuron neuron = outputLayer.getNeurons().get(i);
            double error = neuron.getValue() - expectedOutputs.get(i);
            neuron.setDelta(error * activationFunction.derivative(neuron.getInputValue()));
        }
    }

    private void calculateDeltasForPreviousLayer(Layer currentLayer, Layer previousLayer) {
        for (int prevNeuronIndex = 0; prevNeuronIndex < previousLayer.getNumNeurons(); ++prevNeuronIndex) {
            Neuron previousNeuron = previousLayer.getNeurons().get(prevNeuronIndex);
            double delta = 0;
            for (int weightIndex = 0; weightIndex < previousNeuron.getWeights().size(); ++weightIndex) {
                Neuron currentNeuron = currentLayer.getNeurons().get(weightIndex);
                delta += previousNeuron.getWeights().get(weightIndex) * currentNeuron.getDelta();
            }
            delta = delta * activationFunction.derivative(previousNeuron.getInputValue());
            previousNeuron.setDelta(delta);
        }
    }

    private void correctWeightsForPreviousLayer(Layer currentLayer, Layer previousLayer) {
        for (int neuronIndex = 0; neuronIndex < currentLayer.getNumNeurons(); ++neuronIndex) {
            Neuron currentNeuron = currentLayer.getNeurons().get(neuronIndex);
            for (int prevNeuronIndex = 0; prevNeuronIndex < previousLayer.getNumNeurons(); ++prevNeuronIndex) {
                Neuron previousNeuron = previousLayer.getNeurons().get(prevNeuronIndex);
                Double oldWeight = previousNeuron.getWeights().get(neuronIndex);
                Double newWeight = oldWeight - previousNeuron.getValue() * currentNeuron.getDelta() * learningRate;
                previousNeuron.getWeights().set(neuronIndex, newWeight);
            }
        }
    }

    /**
     * Calculates scalar product of corresponding weights and values of neurons from previous layer
     * which are connected with current neuron (with layerIndex and neuronIndex).
     */
    private Double calculateInputValueForNeuron(int layerIndex, int neuronIndex) {
        Layer prevLayer = neuralNetwork.getLayers().get(layerIndex - 1);
        double scalarProduct = 0.;
        for (Neuron neuron : prevLayer.getNeurons()) {
            scalarProduct = scalarProduct + neuron.getValue() * neuron.getWeights().get(neuronIndex);
        }
        return scalarProduct;
    }
}
