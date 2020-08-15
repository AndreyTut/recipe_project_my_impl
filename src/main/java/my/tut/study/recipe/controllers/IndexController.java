package my.tut.study.recipe.controllers;

import my.tut.study.recipe.domain.Category;
import my.tut.study.recipe.domain.UnitOfMeasure;
import my.tut.study.recipe.repositories.CategoryRepository;
import my.tut.study.recipe.repositories.UnitOfMeasureRepository;
import my.tut.study.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        System.out.println("category id: " + categoryOptional.get().getId());
        System.out.println("uom id: " + unitOfMeasureOptional.get().getId());
        model.addAttribute("list", recipeService.getAllAsList());
        return "index";
    }
}
