package behavioral.observer.jdk;

import java.util.Observable;

/**
 * 利用JDK自带的类实现观察者模式
 * 被观察者
 */
public class Zone extends Observable {

    public void publishThreads(Trends trends) {
        System.out.println(trends.getNickName() + "发表了一个动态【" + trends.getContent() + "】" );
        //做标识用
        setChanged();
        //通知所有观察者
        notifyObservers(trends);
    }

}
