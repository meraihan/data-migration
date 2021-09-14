package com.zoracorp.states.service;

import com.zoracorp.states.utils.RarFileFilter;
import com.github.junrar.extract.ExtractArchive;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;

@Service
public class PrepareRarService {

    public void extractRarFile(String filePth, String destFile) {
        FileFilter filter = new RarFileFilter();
        File[] files = new File(filePth).listFiles(filter);
        for (File file: files){
            ExtractArchive extractArchive = new ExtractArchive();
            extractArchive.extractArchive(file, new File(destFile));
        }
    }
}
