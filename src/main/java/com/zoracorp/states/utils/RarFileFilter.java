package com.zoracorp.states.utils;

import java.io.File;

public class RarFileFilter implements java.io.FileFilter {
    @Override
    public boolean accept(File file) {
        return file != null &&
                file.isFile() &&
                file.canRead() &&
                (file.getName().endsWith("rar"));
    }
}