package behavioral.observer.jdk;

public class Test {

    public static void main(String[] args) {
        Zone zone = new Zone();
        Friends friends = new Friends();
        friends.setFriendName("张三丰");

        Trends trends = new Trends();
        trends.setNickName("张无忌");
        trends.setContent("祝师傅长命百岁");
        zone.addObserver(friends);
        zone.publishThreads(trends);
    }

}
