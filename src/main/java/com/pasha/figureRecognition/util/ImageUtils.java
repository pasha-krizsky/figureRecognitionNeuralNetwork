package com.pasha.figureRecognition.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    public static Double[][] convertBlackAndWhiteImageToArray(String pathToImage) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(pathToImage));
        } catch (IOException e) {
            throw new RuntimeException("Cannot read image from file", e);
        }

        Double[][] pixels = new Double[image.getWidth()][];

        for (int x = 0; x < image.getWidth(); x++) {
            pixels[x] = new Double[image.getHeight()];
            for (int y = 0; y < image.getHeight(); y++) {
                pixels[x][y] = image.getRGB(x, y) == 0xFFFFFFFF ? 0.0 : 1.0;
            }
        }

        return pixels;
    }
}
