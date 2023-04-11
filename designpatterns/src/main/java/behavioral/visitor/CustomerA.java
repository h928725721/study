package behavioral.visitor;

/**
 * 访问者
 * 访问者肯定是经常变化的，我们只需要新增实现类就可以
 */
public class CustomerA implements ICustomer{
    @Override
    public void visit(Meat meat) {
        System.out.println("肉类：" + meat.getPrice());
    }

    @Override
    public void visit(Cabbage cabbage) {
        System.out.println("时蔬：" + cabbage.getPrice());
    }
}
