package creation.singleton.expand;

import java.util.ArrayList;
import java.util.Random;

/**
 * 皇帝类，
 */
public class Emperor {
    private static final int maxNumOfEmperor = 2;
    //每个皇帝都有名字，使用一个arrayList来容纳，每个对象的私有属性
    private static final ArrayList<String> nameList = new ArrayList<>();
    //容纳所有的皇帝实例
    private static final ArrayList<Emperor> emperorList = new ArrayList<>();
    //当前皇帝序列号
    private static int countNumOfEmperor = 0;

    static {
        for (int i = 0; i < maxNumOfEmperor; i++) {
            emperorList.add(new Emperor("皇" + (i + 1) + "帝"));
        }
    }

    /**
     * 目的是不产生第二个皇帝
     */
    private Emperor () {

    }
    private Emperor(String name) {
        nameList.add(name);
    }

    /**
     * 随机获得一个皇帝的对象
     * @return
     */
    public static Emperor getInstance() {
        Random random = new Random();
        countNumOfEmperor = random.nextInt(maxNumOfEmperor);
        return emperorList.get(countNumOfEmperor);
    }

    public void say() {
        System.out.println(nameList.get(countNumOfEmperor));
    }
}
