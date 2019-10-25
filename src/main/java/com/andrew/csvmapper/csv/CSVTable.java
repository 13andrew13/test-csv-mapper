package com.andrew.csvmapper.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVTable {
    private List<String> header = new ArrayList<>();
    private List<List<String>> values = new ArrayList<>();

    private CSVTable() {
    }

    public static CSVTable readFromFile(BufferedReader reader) throws IOException {
        CSVTable table = new CSVTable();
        String header = reader.readLine();
        table.parseHeader(header);
        String rowValue = reader.readLine();
        while (rowValue != null) {
            table.parseRowValue(rowValue);
            rowValue = reader.readLine();
        }
        return table;
    }

    public void parseHeader(String header) {
        this.header.addAll(Arrays.asList(header.split(",")));
    }

    public void parseRowValue(String rowValue) {
        this.values.add(Arrays.asList(rowValue.split(",")));
    }

    public List<String> getHeader() {
        return header;
    }

    public List<List<String>> getValues() {
        return values;
    }
}
