package com.zoracorp.states;

import com.zoracorp.states.service.PrepareExcelService;
import com.zoracorp.states.service.PrepareRarService;
import com.zoracorp.states.service.PrepareZipService;
import com.zoracorp.states.utils.FileLocation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@Slf4j
@SpringBootTest
public class Main {

    @Autowired
    private PrepareZipService prepareZipService;
    @Autowired
    private PrepareRarService prepareRarService;
    @Autowired
    private PrepareExcelService prepareExcelService;

    @Test
    public void process() throws IOException {
        prepareZipService.unzip(FileLocation.ZIP_FILE_LOCATION, FileLocation.ZIP_FILE_DESTINATION);
        log.info("Unzip Process Done!!!...............................................");
        prepareRarService.extractRarFile(FileLocation.RAR_FILE_LOCATION, FileLocation.RAR_FILE_DESTINATION);
        log.info("Rar Extraction Done!!!..............................................");
        prepareExcelService.processExcelFile(FileLocation.EXCEL_FILE_LOCATION);
        log.info("Excels Data Reading Done!!!.........................................");
    }
}
