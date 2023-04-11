package behavioral.observer;

public class WeatherDisplay implements Observer{
    private Subject subject;
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

    /**
     * 注册监听对象
     * @param subject
     */
    public WeatherDisplay(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public void display() {
        System.out.println("当前最新的温度为：" + temperature + ",湿度为：" + humidity +
                ",气压为：" + pressure);
    }
}
