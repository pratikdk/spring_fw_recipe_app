package course.sf.sfg_recipe_app.repositories;

import course.sf.sfg_recipe_app.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    UnitOfMeasure findByName(String name);
}