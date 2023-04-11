package behavioral.visitor;

public class Test {
    public static void main(String[] args) {
        IRecipe meat = new Meat();
        RestaurantMenu restaurantMenu = new RestaurantMenu(meat);
        restaurantMenu.addRecipe(new Cabbage());
        restaurantMenu.display(new CustomerA());
    }
}
