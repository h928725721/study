package behavioral.observer;

import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 被观察者
 */
@NoArgsConstructor
public class WeatherData implements Subject{
    private final List<Observer> observers = Lists.newArrayList();

    /**
     * 温度
     */
    private float temperature;
    /**
     * 湿度
     */
    private float humidity;
    /**
     * 气压
     */
    private float pressure;

    public void setMessurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i > 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature, humidity, pressure);
        }
    }
}
