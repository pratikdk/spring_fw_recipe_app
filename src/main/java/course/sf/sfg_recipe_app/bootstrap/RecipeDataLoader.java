package course.sf.sfg_recipe_app.bootstrap;

import course.sf.sfg_recipe_app.domain.*;
import course.sf.sfg_recipe_app.repositories.CategoryRepository;
import course.sf.sfg_recipe_app.repositories.RecipeRepository;
import course.sf.sfg_recipe_app.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class RecipeDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public RecipeDataLoader(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(this.getRecipes());
        System.out.println("Recipe 1 saved.");
    }

    private List<Recipe> getRecipes() {
        // Get all unit of measures
        Map<String, UnitOfMeasure> uomMap = new HashMap<>();
        unitOfMeasureRepository.findAll().forEach(unitOfMeasure -> uomMap.put(unitOfMeasure.getName(), unitOfMeasure));
        uomMap.forEach((s, unitOfMeasure) -> {
            if (unitOfMeasure == null) {
                throw new RuntimeException("Unit of measure " + s + " can't be null.");
            }
        });

        // Get all categories
        Map<String, Category> catMap = new HashMap<>();
        categoryRepository.findAll().forEach(category -> catMap.put(category.getDescription(), category));

        // Define recipe list
        List<Recipe> recipes = new ArrayList<>();

        // First Recipe
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Perfect Guacamole");
        recipe1.setPrepTime(10);
        recipe1.setCookTime(10);
        recipe1.setServings(4);
        recipe1.setSource("Mexico");
        recipe1.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipe1.setDifficulty(Difficulty.EASY);
        Set<Ingredient> r1Ingredients = new HashSet<>();
        r1Ingredients.add(new Ingredient("avocados", new BigDecimal(2), recipe1, uomMap.get("each")));
        r1Ingredients.add(new Ingredient("salt", new BigDecimal(.4), recipe1, uomMap.get("teaspoon")));
        r1Ingredients.add(new Ingredient("lemon juice", new BigDecimal(1), recipe1, uomMap.get("tablespoon")));
        r1Ingredients.add(new Ingredient("minced red onion", new BigDecimal(4), recipe1, uomMap.get("tablespoon")));
        r1Ingredients.add(new Ingredient("serrano chili", new BigDecimal(2), recipe1, uomMap.get("each")));
        r1Ingredients.add(new Ingredient("cilantro finely chopped", new BigDecimal(2), recipe1, uomMap.get("tablespoon")));
        r1Ingredients.add(new Ingredient("ground black pepper", new BigDecimal(1), recipe1, uomMap.get("pinch")));
        r1Ingredients.add(new Ingredient("tortilla chips", null, recipe1, uomMap.get("as per need")));
        recipe1.setIngredients(r1Ingredients);
        recipe1.setDirections("Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "Add remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n");
        Set<Note> r1Notes = new HashSet<>();
        r1Notes.add(new Note(recipe1, "Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving."));
        r1Notes.add(new Note(recipe1, "Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards."));
        recipe1.setNotes(r1Notes);
        recipe1.getCategories().add(catMap.get("Mexican"));

        recipes.add(recipe1);

        return recipes;
    }
}
