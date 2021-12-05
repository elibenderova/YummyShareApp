package bg.softuni.yummyshare.web;

import bg.softuni.yummyshare.model.binding.RecipeAddBindingModel;
import bg.softuni.yummyshare.model.service.RecipeAddServiceModel;
import bg.softuni.yummyshare.service.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final ModelMapper modelMapper;

    public RecipeController(RecipeService recipeService, ModelMapper modelMapper) {
        this.recipeService = recipeService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public RecipeAddBindingModel recipeAddBindingModel() {
        return new RecipeAddBindingModel();
    }

    @GetMapping("/all")
    public String allRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());

        return "all-recipes";
    }

    @GetMapping("/add")
    public String addRecipe() {
        return "add-recipe";
    }

    @PostMapping("/add")
    public String confirmAddRecipe(@Valid RecipeAddBindingModel recipeAddBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    @AuthenticationPrincipal UserDetails principal) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeAddBindingModel", recipeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeAddBindingModel", bindingResult);

            return "redirect:add";
        }

        RecipeAddServiceModel recipeAddServiceModel = modelMapper.map(recipeAddBindingModel, RecipeAddServiceModel.class);
        recipeAddServiceModel.setAuthor(principal.getUsername());

        recipeService.addRecipe(recipeAddServiceModel, principal);
        return "redirect:all";
    }

    @GetMapping("/details")
    public String showDetails(){
        return "details-recipe";
    }

}