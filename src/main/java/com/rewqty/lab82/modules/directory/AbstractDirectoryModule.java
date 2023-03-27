package com.rewqty.lab82.modules.directory;

import com.rewqty.lab82.modules.Module;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractDirectoryModule implements Module {
    @Override
    public boolean isSupportedFormat(File file) {
        return file.isDirectory();
    }
}
