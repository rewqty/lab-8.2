package com.rewqty.lab82.modules.audio;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class NameAudio extends AbstractAudioModule {

    @Override
    public String getDescription() {
        return "Вывод названия трека из тегов";
    }

    @Override
    public void function(File file) {
        String title = null;

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        try {
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                title = reader.lines()
                        .filter(line -> line.contains("title"))
                        .findFirst()
                        .orElse(null);
            }
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл: " + file.getAbsolutePath());
        }

        if(title == null) {
            System.out.println("У трека нет названия в тегах ;(");
        } else {
            int start = title.indexOf('\"') + 1;
            int end = title.indexOf('\"', start);
            System.out.println(title.substring(start, end));
        }
    }
}
