package com.andrew.csvmapper.json;

public interface JsonEntry {
    String toJsonString();

    void setValue(String header, String value);
}
