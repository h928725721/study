package structural.combination;

/**
 * 菜单项
 * 拥有名称和价格属性，但是不支持新增、删除、打印操作
 */
public class MenuItem extends MenuComponent{
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void print() {
        System.out.println(getName() + "---------" + getPrice());

    }
}
