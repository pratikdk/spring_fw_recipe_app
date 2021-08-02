package course.sf.sfg_recipe_app.controllers;


import course.sf.sfg_recipe_app.domain.Category;
import course.sf.sfg_recipe_app.domain.UnitOfMeasure;
import course.sf.sfg_recipe_app.repositories.CategoryRepository;
import course.sf.sfg_recipe_app.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;

    public IndexController(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model) {

        Category cat = categoryRepository.findByDescription("American");
        UnitOfMeasure uom = unitOfMeasureRepository.findByName("teaspoon");

        System.out.println("Category id is: " + cat.getId());
        System.out.println("UnitOfMeasure id is: " + uom.getId());

        return "index";
    }
}
