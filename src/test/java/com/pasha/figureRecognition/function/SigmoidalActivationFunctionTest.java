package com.pasha.figureRecognition.function;

import com.pasha.figureRecognition.logic.function.SigmoidalActivationFunction;
import com.pasha.figureRecognition.logic.function.ActivationFunction;
import org.junit.Assert;
import org.junit.Test;

public class SigmoidalActivationFunctionTest {

    @Test
    public void calculate() {
        ActivationFunction sigmoid = new SigmoidalActivationFunction();
        double x = 0.5;
        double res = sigmoid.calculate(x);
        Assert.assertEquals(0.622, res, 0.01);
        System.out.println(res);
    }
}