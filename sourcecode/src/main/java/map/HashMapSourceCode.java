package map;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class HashMapSourceCode {

    private Map<String,Object> map = new HashMap<>();


    public static void main(String[] args) {
        ArrayList<Integer> list1 = Lists.newArrayList();
        ArrayList<Integer> list2 = Lists.newArrayList();
        for (int i = 0; i < 10000000; i++) {
            list1.add(i);
            list2.add(10000000 - i);
        }



        long l3 = System.currentTimeMillis();
        HashSet<Integer> set1 = new HashSet<>(list1);
        List<Integer> collect = list2.stream()
                .filter(set1::contains)
                .collect(Collectors.toList());
        long l4 = System.currentTimeMillis();
        System.out.println(l4 - l3);


    }

}
