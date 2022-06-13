package com.tr3ntu.Boiler.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DataHandling {

    public BufferedImage bufferedImage(String imageString) throws IOException {
        String imageDataBytes = imageString.substring(imageString.indexOf(",")+1);
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(imageDataBytes);
        return ImageIO.read(new ByteArrayInputStream(imageBytes));
    }

    public void imageHandling(BufferedImage img) throws IOException {
        File outputfile = new File("image.png");
        ImageIO.write(img, "png", outputfile);
    }
}
