package com.andrew.csvmapper.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonArray implements JsonEntry {
    private Map<Integer, JsonEntry> jsons = new HashMap<>();

    public JsonArray() {
    }

    public JsonArray(List<JsonEntry> jsons) {
        this.jsons = new HashMap<>();
        for (int i = 0; i < jsons.size(); i++) {
            this.jsons.put(i, jsons.get(i));
        }
    }

    public Map<Integer, JsonEntry> getJsons() {
        return jsons;
    }

    @Override
    public String toJsonString() {
        List<String> strings = jsons.values().stream().map(JsonEntry::toJsonString).collect(Collectors.toList());
        String value = String.join(", ", strings);
        return String.format("[\n%s\n]", value);
    }

    @Override
    public void setValue(String header, String value) {
        String[] firstPart = header.split("\\[");
        String[] secondPart = firstPart[1].split("]");
        String index = secondPart[0];
        int i = Integer.parseInt(index);
        if (secondPart.length > 1) {
            JsonEntry element = Optional.ofNullable(jsons.get(i)).orElse(new JsonObject());
            element.setValue(secondPart[1].substring(1), value);
            jsons.put(i, element);
        } else {
            JsonEntry element = Optional.ofNullable(jsons.get(i)).orElse(new JsonValue());
            element.setValue(header, value);
            jsons.put(i, element);
        }

    }

}
