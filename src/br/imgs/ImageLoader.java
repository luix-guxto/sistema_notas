package br.imgs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader {

    private BufferedImage load(String path)throws IOException {
        InputStream ip = new BufferedInputStream(new FileInputStream(path));
        return ImageIO.read(ip);
    }

    public static BufferedImage loadImage(String path) {
        ImageLoader loader = new ImageLoader();
        try {
            return loader.load(path);
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
