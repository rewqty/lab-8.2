package com.rewqty.lab82.modules.image;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

@Component
public class Resolution extends AbstractImageModule {

    @Override
    public String getDescription() {
        return "Выводит разрешение картинки";
    }

    @Override
    public void function(File file) {
        try {
            BufferedImage img = ImageIO.read(file);
            System.out.println("Высота: " + img.getHeight() + " Длина: " + img.getWidth());
        } catch(IOException e) {
            System.out.println("Не удалось прочитать файл " + file.getAbsolutePath());
        }
    }
}
