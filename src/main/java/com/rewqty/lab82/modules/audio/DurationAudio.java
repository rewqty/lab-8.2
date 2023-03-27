package com.rewqty.lab82.modules.audio;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class DurationAudio extends AbstractAudioModule {

    @Override
    public String getDescription() {
        return "вывод длительности в секундах";
    }

    @Override
    public void function(File file) {
        String duration = null;

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());

        try {
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                duration = reader.lines()
                        .filter(line -> line.contains("duration"))
                        .findFirst()
                        .orElse(null);
            }
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл: " + file.getAbsolutePath());
        }

        if(duration == null) {
            System.out.println("У трека нет длительности О_о");
        } else {
            int start = duration.indexOf('\"') + 1;
            int end = duration.indexOf('\"', start);
            System.out.println("Длительность трека: " + duration.substring(start, end) + " секунд");
        }
    }
}
