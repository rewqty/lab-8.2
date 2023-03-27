package com.rewqty.lab82.modules.text;

import com.rewqty.lab82.modules.FileUtils;
import com.rewqty.lab82.modules.Module;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractTextModule implements Module {
    private static final List<String> _supportedFormat = Arrays.asList("txt", "doc", "html", "xml", "ini", "json", "log");

    @Override
    public boolean isSupportedFormat(File file) {
        return _supportedFormat.contains(FileUtils.getExtension(file));
    }
}
