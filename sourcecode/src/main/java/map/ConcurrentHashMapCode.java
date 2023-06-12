package map;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapCode {

    private static Map<Integer,String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        HashSet<Object> set = new HashSet<>();
        set.add(args);


    }

}
