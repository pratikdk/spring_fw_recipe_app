package course.sf.sfg_recipe_app.controllers;

import course.sf.sfg_recipe_app.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipeList")
public class RecipeListController {
    private final RecipeRepository recipeRepository;

    public RecipeListController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"", "/", ".html"})
    public String getRecipeList(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipeList";
    }
}
