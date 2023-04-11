package com.candu.concurrency.part1.safe;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class PublishingVehicleTracker {

    private final ConcurrentHashMap<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> locations) {
        this.locations = new ConcurrentHashMap<>(locations);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, SafePoint> getLocations() {
        return unmodifiableMap;
    }

    public SafePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new SafePoint(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
        locations.get(id).set(x,y);
    }

}

@ThreadSafe
class SafePoint {
    @GuardedBy("this")
    public int x;
    @GuardedBy("this")
    public int y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * get()方法返回的是x,y的一个拷贝数组
     * 如果分别返回x,y,那么并发环境可能导致获取的间隙有线程修改了x或y,从而获取到一个从未有过的坐标
     */
    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public synchronized void set(int x,int y) {
        this.x = x;
        this.y = y;
    }
}
