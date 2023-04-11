package com.candu.concurrency.part1.safe;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AttributeStore {
    private final Map<String,String> attribute = new HashMap<>();
    public boolean userLocationMatches(String name,String regexp) {
        String key = "users." + name + ".location";
        String location;
        synchronized (this) {
            location = attribute.get(key);
        }
        if (location == null) {
            return false;
        } else {
            return Pattern.matches(regexp,location);
        }
    }
}
