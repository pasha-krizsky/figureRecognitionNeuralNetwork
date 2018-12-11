package com.pasha.figureRecognition.logic;

import org.junit.Before;
import org.junit.Test;

public class TrainingSampleFromImageProviderTest {

    private TrainingSampleProvider provider;

    @Before
    public void setUp() {
        provider = new TrainingSampleFromImageProvider("D:\\projects\\java\\figureRecognition\\src\\main\\resources\\all_samples");
    }

    @Test
    public void provide() {
        Double[][] res = provider.provide();
        for (int i = 0; i < res.length; ++i) {
            for (int j = 0; j < res[0].length; ++j) {
                if (j % 7 == 0) {
                    System.out.println();
                }
                System.out.print(res[i][j]);
            }
            System.out.println();
        }
    }
}