package com.rewqty.lab82.modules;

import java.io.File;

public class FileUtils {
    public static String getExtension(File file) {
        String nameFile = file.getName();
        int index = nameFile.lastIndexOf('.');
        return (index == -1 || index == nameFile.length() - 1)
                ? ""
                : nameFile.substring(index + 1).toLowerCase();
    }

    public static String removeExtension(File file) {
        String nameFile = file.getName();
        int index = nameFile.lastIndexOf('.');
        return (index == -1 || index == nameFile.length() - 1)
                ? nameFile
                : nameFile.substring(0, index);
    }
}
