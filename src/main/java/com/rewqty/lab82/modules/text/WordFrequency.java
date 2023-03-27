package com.rewqty.lab82.modules.text;

import com.rewqty.lab82.modules.Module;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

@Component
public class WordFrequency extends AbstractTextModule {
    @Override
    public String getDescription() {
        return "Вывод частоты вхождения каждого слова";
    }

    @Override
    public void function(File file) {
        HashMap<String, Integer> freqWord = null;
        try (Stream<String> lines = Files.lines(file.toPath())) {
            freqWord = lines.map(x -> x.replaceAll("\\pP", "").toLowerCase())
                    .flatMap(x -> Stream.of(x.split("\\s")))
                    .collect(HashMap::new, (m, c) -> m.put(c, m.getOrDefault(c,0) + 1), HashMap::putAll);

        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл " + file.getAbsolutePath());
        }

        if(freqWord == null || freqWord.isEmpty()) {
            System.out.println("Файл пустой");
        } else {
            freqWord.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .map(pair -> pair.getKey() + ": " + pair.getValue())
                    .forEach(System.out::println);
        }

    }
}
