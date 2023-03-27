package com.rewqty.lab82.modules.image;
import com.rewqty.lab82.modules.FileUtils;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class GrayScale extends AbstractImageModule {

    @Override
    public String getDescription() {
        return "Создать чёрно-белую версию картинки";
    }

    @Override
    public void function(File file) {
        BufferedImage img = null;
        try {
           img = ImageIO.read(file);
        } catch(IOException e) {
            System.out.println("Не удалось прочитать файл " + file.getAbsolutePath());
        }

        int width = Objects.requireNonNull(img).getWidth();
        int height = img.getHeight();

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color color = new Color(img.getRGB(x, y));
                int avg = (color.getRed() + color.getBlue() + color.getGreen()) / 3;
                Color newColor = new Color(avg, avg, avg);
                img.setRGB(x, y, newColor.getRGB());
            }
        }

        try {
            String newFileName = FileUtils.removeExtension(file) + "-gray." + FileUtils.getExtension(file);
            File output = new File(newFileName);
            ImageIO.write(img, FileUtils.getExtension(file),output);
            System.out.println("Картинка готова и сохранена под названием: " + newFileName);
        } catch (IOException e) {
            System.out.println("Не удалось создать чёрно-белую картинку ;(");
        }
    }
}
