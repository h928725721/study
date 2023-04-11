package creation.prototype;

public class Test {
    public static void main(String[] args) {
        Prototype pro = new ConcreatePrototype("prototype");
        Prototype pro2 = pro.clone();
        System.out.println(pro.getName());
        System.out.println(pro2.getName());
    }
}
