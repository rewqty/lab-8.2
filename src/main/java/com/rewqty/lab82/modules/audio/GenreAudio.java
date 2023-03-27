package com.rewqty.lab82.modules.audio;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class GenreAudio extends AbstractAudioModule {

    @Override
    public String getDescription() {
        return "Вывод жанра трека из тегов";
    }

    @Override
    public void function(File file) {
        String genre = null;

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        try {
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                genre = reader.lines()
                        .filter(line -> line.contains("genre"))
                        .findFirst()
                        .orElse(null);
            }
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл: " + file.getAbsolutePath());
        }

        if(genre == null) {
            System.out.println("У трека нет жанра в тегах ;(");
        } else {
            int start = genre.indexOf('\"') + 1;
            int end = genre.indexOf('\"', start);
            System.out.println("Жанр: " + genre.substring(start, end));
        }
    }
}
