package behavioral.interpreter;

/**
 * 终结表达式-数值表达式
 */
public class NumberExpression implements IExpression{
    private int value;

    public NumberExpression(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public int interpret() {
        return this.value;
    }
}
