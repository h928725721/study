package behavioral.visitor;

public class Cabbage implements IRecipe {
    @Override
    public void accept(ICustomer customer) {
        customer.visit(this);
    }

    public String getPrice(){
        return "44元/份";
    }
}
