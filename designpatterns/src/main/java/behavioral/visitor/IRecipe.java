package behavioral.visitor;

/**
 * 针对系统中拥有的某些固定类型的对象结构，在其内提供一个accept方法用来接受访问者对象的访问。
 */
public interface IRecipe {
    void accept(ICustomer customer);
}
