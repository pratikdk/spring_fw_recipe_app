package course.sf.sfg_recipe_app.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Note extends BaseEntity {
    @Lob
    private String recipeNote;

    @ManyToOne
    private Recipe recipe;

    public Note() {}

    public Note(Recipe recipe, String recipeNote) {
        this.recipe = recipe;
        this.recipeNote = recipeNote;
    }

    public String getRecipeNote() {
        return recipeNote;
    }

    public void setRecipeNote(String recipeNote) {
        this.recipeNote = recipeNote;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
