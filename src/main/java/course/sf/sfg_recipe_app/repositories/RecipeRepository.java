package course.sf.sfg_recipe_app.repositories;

import course.sf.sfg_recipe_app.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
