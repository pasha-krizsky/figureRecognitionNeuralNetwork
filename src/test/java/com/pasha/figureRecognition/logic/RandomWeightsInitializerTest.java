package com.pasha.figureRecognition.logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RandomWeightsInitializerTest {

    private WeightsInitializer weightsInitializer;

    @Before
    public void setUp() {
        weightsInitializer = new RandomWeightsInitializer();
    }

    @Test
    public void initialize() {
        List<Double> weights = weightsInitializer.initialize(49);
        System.out.println("Randomly initialized weights");
        System.out.println(weights);
        Assert.assertEquals(49, weights.size());
        for (Double weight: weights) {
            Assert.assertTrue(weight >= 0);
            Assert.assertTrue(weight <= 1);
        }
    }
}