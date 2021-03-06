package my.tut.study.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import my.tut.study.recipe.commands.IngredientCommand;
import my.tut.study.recipe.domain.Recipe;
import my.tut.study.recipe.services.IngredientService;
import my.tut.study.recipe.services.RecipeService;
import my.tut.study.recipe.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        IngredientCommand command = ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id));
        model.addAttribute("ingredient", command);
        return "recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String update(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.getAll());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand command = ingredientService.save(ingredientCommand);
        return "redirect:/recipe/" + ingredientCommand.getRecipeId() + "/ingredient/" + command.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String addNew(@PathVariable String recipeId, Model model) {
        //check if recipeId not bad
        Recipe recipe = recipeService.findById(Long.valueOf(recipeId));
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", unitOfMeasureService.getAll());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String delete(@PathVariable String recipeId, @PathVariable String id) {
        Long recipeIdLong = Long.valueOf(recipeId);
        Long idLong = Long.valueOf(id);
        ingredientService.deleteByRecipeIdAndIngredientId(recipeIdLong, idLong);
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
