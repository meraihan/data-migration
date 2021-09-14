package com.zoracorp.states.service;

import com.zoracorp.states.domain.Persons;
import com.zoracorp.states.repository.PersonRepository;
import com.zoracorp.states.utils.ExcelFileFilter;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrepareExcelService {

    private @Autowired
    PersonRepository personRepository;

    public void processExcelFile(String fileLocation) {
        File directory = new File(fileLocation);
        File[] folderFiles = directory.listFiles();
        for(File folderFile: folderFiles){
            FileFilter filter = new ExcelFileFilter();
            File[] files = folderFile.listFiles(filter);
            for (File file : files) {
                List<Map> excelDataList = readExcelData(file);
                saveToDB(excelDataList);
                System.out.println(file);
            }
        }

    }

    public List<Map> readExcelData(File file) {
        List<Map> rowDataList = new ArrayList<>();
        try {
            System.out.println("Excel data loading...");
            InputStream is = new FileInputStream(file);
            StreamingReader reader = StreamingReader.builder()
                    .rowCacheSize(10000000)    // number of rows to keep in memory (defaults to 10)
                    .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                    .sheetIndex(0)        // index of sheet to use (defaults to 0)
                    .read(is);            // InputStream or File for XLSX file (required)

            Map colDataMap;
            int i = 0;
            int rowNum=0;
            for (Row row : reader) {
                colDataMap = new HashMap();
                i = 0;
                for (Cell cell : row) {
                    colDataMap.put(i, cell.getStringCellValue());
                    i++;
                }
                rowDataList.add(colDataMap);
                rowNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDataList;
    }

    public void saveToDB(List<Map> excelDataList) {
        List<Persons> personsList = new ArrayList<>();
        for (int k = 1; k < excelDataList.size(); k++) {
            Persons persons = new Persons();
            persons.setNumber(excelDataList.get(k).get(0).toString());
            persons.setFirstName(excelDataList.get(k).get(1).toString());
            persons.setLastName(excelDataList.get(k).get(2).toString());
            persons.setCountry("Nigeria");
            persons.setState(excelDataList.get(k).get(3).toString());
            persons.setLGA(excelDataList.get(k).get(4).toString());
            persons.setGender(excelDataList.get(k).get(5).toString());
            personsList.add(persons);
        }
        personRepository.saveAll(personsList);
    }

}

