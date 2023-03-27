package com.rewqty.lab82.modules.directory;

import com.rewqty.lab82.modules.Module;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

@Component
public class SizeFiles extends AbstractDirectoryModule {
    @Override
    public String getDescription() {
        return "Подсчет размера всех файлов в каталоге";
    }

    @Override
    public void function(File file) {
        Arrays.stream(Objects.requireNonNull(file.listFiles(File::isFile)))
                .map(x -> x.getName() + ":\t" + x.length() + " байт")
                .forEach(System.out::println);
    }
}
