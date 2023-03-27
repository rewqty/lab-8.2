package com.rewqty.lab82.modules.audio;

import com.rewqty.lab82.modules.Module;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.rewqty.lab82.modules.FileUtils;

public abstract class AbstractAudioModule implements Module {
    private final List<String> _supportedFormat = Arrays.asList(
            "mp3", "ogg", "wav", "aac", "flac"
    );
    @Override
    public boolean isSupportedFormat(File file) {
        return _supportedFormat.contains(FileUtils.getExtension(file));
    }
}
