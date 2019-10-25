package com.andrew.csvmapper;

import com.andrew.csvmapper.csv.CSVTable;
import com.andrew.csvmapper.json.JsonEntry;
import com.andrew.csvmapper.json.JsonObject;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        File file = new File("/Users/andrii/IdeaProjects/test-csv-mapper/src/main/resources/test.csv");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            CSVTable table = CSVTable.readFromFile(reader);
            System.out.println(Arrays.toString(table.getHeader().toArray()));
            JsonEntry json = CSVJsonParser.parseFromCsv(table);
            System.out.println(json.toJsonString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
