package com.andrew.csvmapper.json;

public class JsonValue implements JsonEntry {
    private String value;

    public JsonValue() {
    }

    @Override
    public String toJsonString() {
        return String.format("\"%s\"", value);
    }

    @Override
    public void setValue(String header, String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
