package com.pasha.figureRecognition.logic;

/**
 * This interface must be used to provide training sample.
 * <p>
 * For example, we can obtain training sample after image processing or
 * just use dummy implementations for testing purposes.
 */
@FunctionalInterface
public interface TrainingSampleProvider {
    Double[][] provide();
}
