package com.pasha.figureRecognition.logic;

import com.pasha.figureRecognition.util.ImageUtils;

import java.io.File;
import java.util.Objects;

public class TrainingSampleFromImageProvider implements TrainingSampleProvider {

    private String pathToFolderWithImages;

    public TrainingSampleFromImageProvider(String pathToFolderWithImages) {
        this.pathToFolderWithImages = pathToFolderWithImages;
    }

    @Override
    public Double[][] provide() {
        File folderWithImages = new File(pathToFolderWithImages);
        File[] files = folderWithImages.listFiles();

        if (!folderWithImages.isDirectory()) {
            throw new IllegalArgumentException("Not directory!");
        }

        Double[][] result = new Double[Objects.requireNonNull(files).length][];
        for (int fileIndex = 0; fileIndex < files.length; ++fileIndex) {
            Double[][] pixels = ImageUtils.convertBlackAndWhiteImageToArray(files[fileIndex].getAbsolutePath());
            result[fileIndex] = new Double[pixels.length * pixels[0].length];
            int i = 0;
            for (Double[] row : pixels) {
                for (int y = 0; y < pixels[0].length; y++) {
                    result[fileIndex][i] = row[y];
                    ++i;
                }
            }
        }
        return result;
    }
}
