package com.rewqty.lab82.modules.directory;

import com.rewqty.lab82.modules.Module;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

@Component
public class ListFile extends AbstractDirectoryModule {
    @Override
    public String getDescription() {
        return "Выводит список файлов в каталоге";
    }

    @Override
    public void function(File file) {
        Arrays.stream(Objects.requireNonNull(file.listFiles(File::isFile)))
                .map(File::getName)
                .forEach(System.out::println);
    }
}
