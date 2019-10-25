package com.andrew.csvmapper;

import com.andrew.csvmapper.csv.CSVTable;
import com.andrew.csvmapper.json.JsonArray;
import com.andrew.csvmapper.json.JsonEntry;
import com.andrew.csvmapper.json.JsonObject;
import org.junit.Test;

import java.io.*;

public class MainTest {
    @Test
    public void testCsv() {
        File file = new File("/Users/andrii/IdeaProjects/test-csv-mapper/src/main/resources/test.csv");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            CSVTable table = CSVTable.readFromFile(reader);

            JsonEntry json = CSVJsonParser.parseFromCsv(table);
            JsonArray array = (JsonArray) json;
            JsonEntry entry = array.getJsons().get(0);
            JsonObject entryObject = (JsonObject) entry;


            assert json instanceof JsonArray;
            assert entry instanceof JsonObject;
            assert entryObject != null;
            assert entryObject.getValueByKey("empId") != null;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
