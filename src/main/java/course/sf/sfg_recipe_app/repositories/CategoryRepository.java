package course.sf.sfg_recipe_app.repositories;

import course.sf.sfg_recipe_app.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByDescription(String description);
}
