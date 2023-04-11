package behavioral.observer.jdk;

import lombok.Data;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * 观察者类
 */
@Data
public class Friends implements Observer {

    private String friendName;


    @Override
    public void update(Observable o, Object arg) {
        Trends trends = new Trends();
        if (Objects.nonNull(arg) && arg instanceof Trends) {
            trends = (Trends) arg;
        }
        System.out.println(this.getFriendName() + "，您好！您收到了来自" + trends.getNickName() +
                "的一条动态【" + trends.getContent() + "】" + "快去点赞吧！");
    }
}
