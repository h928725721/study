package behavioral.observer;


public interface Subject {
    //注册观察对象
    void registerObserver(Observer o);
    //移除观察对象
    void removeObserver(Observer o);
    //通知观察对象
    void notifyObservers();
}
