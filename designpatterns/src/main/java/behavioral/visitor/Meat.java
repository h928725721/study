package behavioral.visitor;

public class Meat implements IRecipe {
    @Override
    public void accept(ICustomer customer) {
        customer.visit(this);
    }

    public String getPrice(){
        return "88元/份";
    }
}
