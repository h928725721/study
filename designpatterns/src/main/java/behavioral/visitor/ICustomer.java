package behavioral.visitor;

public interface ICustomer {
    void visit(Meat meat);
    void visit(Cabbage cabbage);
}
