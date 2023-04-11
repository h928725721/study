package behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenu {
    private List<IRecipe> recipeList = new ArrayList<>();

    public RestaurantMenu(IRecipe recipe) {
        recipeList.add(recipe);
    }

    public void addRecipe(IRecipe recipe){
        recipeList.add(recipe);
    }

    public void display(ICustomer customer){
        for (IRecipe recipe : recipeList){
            recipe.accept(customer);
        }
    }
}
