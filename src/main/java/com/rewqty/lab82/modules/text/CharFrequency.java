package com.rewqty.lab82.modules.text;

import com.rewqty.lab82.modules.Module;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

@Component
public class CharFrequency extends AbstractTextModule {
    @Override
    public String getDescription() {
        return "Вывод частоты вхождения каждого символа";
    }

    @Override
    public void function(File file) {
        HashMap<Character, Integer> freqChar = null;
        try (Stream<String> lines = Files.lines(file.toPath())) {
            freqChar = lines.flatMapToInt(String::chars)
                    .mapToObj(x -> (char) x)
                    .collect(HashMap::new, (m, c) -> m.put(c, m.getOrDefault(c, 0) + 1), HashMap::putAll);

        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл " + file.getAbsolutePath());
        }

        if(freqChar == null || freqChar.isEmpty()) {
            System.out.println("Файл пустой");
        } else {
            freqChar.entrySet()
                    .stream()
                    .map(pair -> pair.getKey() + ": " + pair.getValue())
                    .forEach(System.out::println);
        }

    }
}
