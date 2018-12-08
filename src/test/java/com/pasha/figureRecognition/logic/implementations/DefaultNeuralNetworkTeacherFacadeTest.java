package com.pasha.figureRecognition.logic.implementations;

import com.pasha.figureRecognition.logic.DefaultNeuralNetworkTeacherFacade;
import com.pasha.figureRecognition.logic.NeuralNetworkTeacherFacade;
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
        networkTeacherFacade.train();
        System.out.println(networkTeacherFacade.getNeuralNetworkErrorAfterTraining());
    }

}