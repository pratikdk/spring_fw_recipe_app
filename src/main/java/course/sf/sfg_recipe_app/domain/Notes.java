package course.sf.sfg_recipe_app.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Notes extends BaseEntity {

    private String recipeNotes;

    @OneToOne
    private Recipe recipe;

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
