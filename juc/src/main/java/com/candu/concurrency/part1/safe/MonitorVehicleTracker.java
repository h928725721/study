package com.candu.concurrency.part1.safe;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class MonitorVehicleTracker {

    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);

    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such ID : " + id);
        }
        loc.x = x;
        loc.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        HashMap<String, MutablePoint> result = new HashMap<>();
        for (String id : m.keySet()) {
            result.put(id, new MutablePoint(m.get(id)));
        }
        //unmodifiableMap 产生一个只读Map
        return Collections.unmodifiableMap(result);
    }


}

@NotThreadSafe
class MutablePoint {
    public int x;
    public int y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
