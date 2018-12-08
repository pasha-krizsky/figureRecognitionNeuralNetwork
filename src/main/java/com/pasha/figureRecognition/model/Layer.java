package com.pasha.figureRecognition.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent one layer on neural network.
 */
public class Layer {

    private List<Neuron> neurons;
    private int numNeurons;

    public Layer(int numNeurons) {
        this.numNeurons = numNeurons;
        initListOfNeurons();
    }

    private void initListOfNeurons() {
        neurons = new ArrayList<>(numNeurons);
        for (int i = 0; i < numNeurons; ++i) {
            neurons.add(new Neuron());
        }
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public int getNumNeurons() {
        return numNeurons;
    }
}
