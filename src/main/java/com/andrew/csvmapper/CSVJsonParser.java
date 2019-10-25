package com.andrew.csvmapper;

import com.andrew.csvmapper.csv.CSVTable;
import com.andrew.csvmapper.json.JsonArray;
import com.andrew.csvmapper.json.JsonEntry;
import com.andrew.csvmapper.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class CSVJsonParser {
    public static JsonEntry parseFromCsv(CSVTable table) {
        List<JsonEntry> entries = new ArrayList<>();
        table.getValues().forEach(valueList -> {
                    JsonEntry parseEntry = parseCsvRow(table.getHeader(), valueList);
                    entries.add(parseEntry);
                }
        );
        return new JsonArray(entries);
    }

    private static JsonEntry parseCsvRow(List<String> headers, List<String> valueList) {
        JsonObject object = new JsonObject();
        for (int i = 0; i < headers.size(); i++) {
            object.setValue(headers.get(i), valueList.get(i));
        }
        return object;
    }

}
