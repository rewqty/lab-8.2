package com.rewqty.lab82.modules.directory;

import com.rewqty.lab82.modules.Module;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

@Component
public class ListDirectory extends AbstractDirectoryModule {
    @Override
    public String getDescription() {
        return "Выводит список подкаталогов в каталоге";
    }

    @Override
    public void function(File file) {
        Arrays.stream(Objects.requireNonNull(file.listFiles(File::isDirectory)))
                .map(File::getName)
                .forEach(System.out::println);
    }
}
