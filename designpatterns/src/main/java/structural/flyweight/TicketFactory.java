package structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义一个工厂类来管理享元对象
 */
public class TicketFactory {

    private static Map<String ,ITicket> CACHE_POOL = new HashMap<>();

    public static ITicket getTicketInfo(String from,String to) {
        String key = from + "->" + to;
        if (CACHE_POOL.containsKey(key)) {
            System.out.println("使用缓存");
            return CACHE_POOL.get(key);
        }
        System.out.println("未使用缓存");
        TrainTicket trainTicket = new TrainTicket(from,to);
        CACHE_POOL.put(key,trainTicket);
        return trainTicket;
    }

}
