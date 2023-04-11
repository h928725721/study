package structural.combination;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 菜单组件
 * 菜单组件有菜单名和子菜单，但是没有价格，支持添加、删除、打印操作
 */
public class Menu extends MenuComponent{

    private final String name;
    private final List<MenuComponent> menuList = Lists.newArrayList();

    public Menu(String name) {
        this.name = name;
    }

    @Override
    public void add(MenuComponent component) {
        menuList.add(component);
    }

    @Override
    public void remove(MenuComponent component) {
        menuList.remove(component);
    }

    @Override
    public MenuComponent getChild(int i) {
        return menuList.get(i);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void print() {
        System.out.println("---------");
        System.out.println(getName());
        menuList.forEach(MenuComponent::print);
        System.out.println("---------");
    }
}
