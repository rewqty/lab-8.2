package com.rewqty.lab82.modules;

import java.io.File;

public interface Module {
    boolean isSupportedFormat(File file);
    String getDescription();
    void function(File file);
}
