package com.rewqty.lab82.modules.text;

import com.rewqty.lab82.modules.Module;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class CountRow extends AbstractTextModule {

    public String getDescription() {
        return "Подсчёт и отображение количества строк";
    }

    public void function(File file){
        try (Stream<String> lines = Files.lines(file.toPath())) {
            System.out.println("В файле всего " +  lines.count() + " строк");
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл " + file.getAbsolutePath());
        }
    }
}
