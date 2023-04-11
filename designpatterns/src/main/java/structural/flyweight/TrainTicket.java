package structural.flyweight;

import java.math.BigDecimal;

/**
 * 具有享元角色
 */
public class TrainTicket implements ITicket{
    /**
     * 这两个是内部状态
     */
    private final String from;
    private final String to;

    /**
     * 这个是外部状态
     */
    private String seatType;

    public TrainTicket(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void info() {
        System.out.println(from + "->" + to + ":" + seatType + getPrice(seatType));
    }

    @Override
    public void setSeat(String seatType) {
        this.seatType = seatType;
    }
    private BigDecimal getPrice(String seatType){
        BigDecimal value = null;
        switch (seatType){
            case "硬座":
                value = new BigDecimal("100");
                break;
            case "硬卧":
                value = new BigDecimal("200");
                break;
            default:
                value = new BigDecimal("50");
        }
        return value;
    }
}
