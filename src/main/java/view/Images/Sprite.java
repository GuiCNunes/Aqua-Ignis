package view.Images;

import gui.GUI;
import com.googlecode.lanterna.TextColor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Sprite {
    private final BufferedImage image;

    public Sprite(String filepath) throws IOException {
        this.image = loadImage(filepath);
    }

    private BufferedImage loadImage(String filepath) throws IOException {
        ClassLoader cl = getClass().getClassLoader();
        try (InputStream in = cl.getResourceAsStream(filepath)) {
            if (in == null) throw new IOException("Image not found in resources: " + filepath);
            return ImageIO.read(in);
        }
    }

    public void draw(GUI gui, int a, int b) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int argb = image.getRGB(x, y);
                int alpha = (argb >>> 24) & 0xFF;
                if (alpha == 0) continue;

                int r = (argb >>> 16) & 0xFF;
                int g = (argb >>> 8) & 0xFF;
                int bl = argb & 0xFF;

                gui.drawPixel(a + x, b + y, new TextColor.RGB(r, g, bl));;
            }
        }
    }

    public static Sprite load(String path) {
        try {
            return new Sprite(path);
        } catch (IOException e) {
            throw new java.io.UncheckedIOException(e);
        }
    }
}