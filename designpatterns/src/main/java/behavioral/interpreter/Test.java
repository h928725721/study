package behavioral.interpreter;

public class Test {

    public static void main(String[] args) {
        ExpressionContext context = new ExpressionContext("666 + 888 - 777");
        System.out.println(context.calcuate());
        context = new ExpressionContext("123 - 456 + 11");
        System.out.println(context.calcuate());

    }
}
