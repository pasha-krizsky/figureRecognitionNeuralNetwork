package com.pasha.figureRecognition.logic;

import com.pasha.figureRecognition.util.ImageUtils;

import java.io.File;

public class TrainingSampleFromImageProvider implements TrainingSampleProvider {

    private String pathToFolderWithImages;

    public TrainingSampleFromImageProvider(String pathToFolderWithImages) {
        this.pathToFolderWithImages = pathToFolderWithImages;
    }

    @Override
    public Double[][] provide() {
        File folderWithImages = new File(pathToFolderWithImages);
        File[] files = folderWithImages.listFiles();
        Double[][] result = new Double[files.length][];
        for (int fileIndex = 0; fileIndex < files.length; ++fileIndex) {
            Double[][] pixels = ImageUtils.convertBlackAndWhiteImageToArray(files[fileIndex].getAbsolutePath());
            result[fileIndex] = new Double[pixels.length * pixels[0].length];
            int i = 0;
            for (int x = 0; x < pixels.length; x++) {
                for (int y = 0; y < pixels[0].length; y++) {
                    result[fileIndex][i] = pixels[x][y];
                    ++i;
                }
            }
        }
        return result;
    }
}
