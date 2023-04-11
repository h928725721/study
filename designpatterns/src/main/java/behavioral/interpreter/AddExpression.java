package behavioral.interpreter;

/**
 * 非终结表达式-加法表达式
 */
public class AddExpression extends AbstractNonTerminalExpression{
    public AddExpression(IExpression leftExpression, IExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    public int interpret() {
        return this.leftExpression.interpret() + this.rightExpression.interpret();
    }
}
