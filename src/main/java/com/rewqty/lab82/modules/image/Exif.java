package com.rewqty.lab82.modules.image;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Descriptor;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

@Component
public class Exif extends AbstractImageModule {

    @Override
    public String getDescription() {
        return "Вывод информации exif";
    }

    @Override
    public void function(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            ExifIFD0Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            if(directory == null) {
                System.out.println("В файле нет информации exif");
                return;
            }
            for(Tag tag : directory.getTags()) {
                System.out.printf("%s = %s",
                        tag.getTagName(), tag.getDescription());
                System.out.println();
            }
        } catch(IOException | ImageProcessingException e) {
            System.out.println("Не удалось прочитать файл " + file.getAbsolutePath());
        }
    }
}