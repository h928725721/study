package behavioral.interpreter;

/**
 * 非终结表达式
 */
public abstract class AbstractNonTerminalExpression implements IExpression {
    protected IExpression leftExpression;
    protected IExpression rightExpression;

    public AbstractNonTerminalExpression(IExpression leftExpression, IExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
}
