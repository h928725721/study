package behavioral.strategy.comment;

public interface PayStrategy {

    /**
     * @param money 需要付款的金额
     */
    boolean pay(int money);

}
