package oop.project.library.command;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParsedComm {

    private final Map<String, Object> values;

    public ParsedComm() {
        this.values = new LinkedHashMap<String, Object>();
    }

    public void put(String name, Object value) {
        values.put(name, value);
    }

    public Object get(String name) {
        return values.get(name);
    }

    public Map<String, Object> asMap() {
        return values;
    }
}