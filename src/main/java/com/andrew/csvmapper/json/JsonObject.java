package com.andrew.csvmapper.json;

import java.util.*;

public class JsonObject implements JsonEntry {
    private Map<String, JsonEntry> keyValueMap = new HashMap<>();

    public JsonObject() {
    }

    public Map<String, JsonEntry> getKeyValueMap() {
        return keyValueMap;
    }

    public void setKeyValueMap(Map<String, JsonEntry> keyValueMap) {
        this.keyValueMap = keyValueMap;
    }

    @Override
    public String toJsonString() {
        List<String> strings = new ArrayList<>();
        keyValueMap.forEach((key, value) -> strings.add(String.format("\"%s\": %s", key, value.toJsonString())));
        String values = String.join(", \n", strings);
        return String.format("{ %s }", values);
    }

    @Override
    public void setValue(String header, String value) {
        boolean isNestedObject = header.contains(".");
        boolean isArray = header.contains("[");
        if (isArray) {
            List<String> split = new ArrayList<>(Arrays.asList(header.split("\\[")));
            String headerKey = split.get(0);
            if (headerKey.contains(".")) {
                createNestedObject(header, value);
                return;
            }
            JsonEntry entry = keyValueMap.getOrDefault(headerKey, new JsonArray());
            keyValueMap.put(headerKey, entry);
            entry.setValue(header, value);
        } else if (isNestedObject) {
            createNestedObject(header, value);
        } else {
            JsonValue jv = new JsonValue();
            jv.setValue(value);
            keyValueMap.put(header, jv);
        }
    }

    private void createNestedObject(String header, String value) {
        List<String> split = new ArrayList<>(Arrays.asList(header.split("\\.")));
        String headerKey = split.get(0);
        split.remove(0);
        JsonEntry entry = keyValueMap.getOrDefault(headerKey, new JsonObject());
        keyValueMap.put(headerKey, entry);
        entry.setValue(String.join(".", split), value);
    }

    public JsonEntry getValueByKey(String key) {
        return keyValueMap.get(key);
    }

}
