package bg.softuni.yummyshare.service;


import bg.softuni.yummyshare.model.service.RecipeAddServiceModel;
import bg.softuni.yummyshare.model.view.RecipeViewModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.List;

public interface RecipeService {
    void addRecipe(RecipeAddServiceModel recipeAddServiceModel, UserDetails principal) throws IOException;
    List<RecipeViewModel> getAllRecipes();
//    RecipeViewModel findById(Long id);
}
