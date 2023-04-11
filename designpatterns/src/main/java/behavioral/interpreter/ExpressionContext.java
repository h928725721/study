package behavioral.interpreter;

import java.util.Stack;

public class ExpressionContext {
    /**
     * 当前运算结果
     */
    private Integer currValue;


    private Stack<IExpression> stack = new Stack<>();

    public ExpressionContext(String expression) {
        this.parse(expression);
    }

    private void parse(String expression) {
        String[] elementArr = expression.split(" ");
        for (int i=0;i<elementArr.length;i++){
            String element = elementArr[i];
            if (element.equals("+")){
                //栈内元素出栈
                IExpression leftExpression = stack.pop();
                //取出+号后的下一个元素
                IExpression rightExpression = new NumberExpression(elementArr[++i]);
                IExpression addExpression = new AddExpression(leftExpression,rightExpression);
                //将计算结果入栈
                stack.push(new NumberExpression(addExpression.interpret() + ""));
            }else if (element.equals("-")){
                //栈内元素出栈
                IExpression leftExpression = stack.pop();
                //取出-号后的下一个元素
                IExpression rightExpression = new NumberExpression(elementArr[++i]);
                IExpression subExpression = new SubExpression(leftExpression,rightExpression);
                //将计算结果入栈
                stack.push(new NumberExpression(subExpression.interpret() + ""));
            }else{
                //如果是数字则直接入栈
                stack.push(new NumberExpression(element));
            }
        }
    }

    public int calcuate(){
        //经过前面解析，到这里stack内只会剩下唯一一个数字，即运算结果
        return stack.pop().interpret();
    }



}
