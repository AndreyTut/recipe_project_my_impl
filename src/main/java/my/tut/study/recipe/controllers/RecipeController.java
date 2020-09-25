package my.tut.study.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import my.tut.study.recipe.commands.RecipeCommand;
import my.tut.study.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
        RecipeCommand command = new RecipeCommand();
//        command.setDescription("new recipe");
        model.addAttribute("recipe", command);
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String SaveOrEdit(@ModelAttribute RecipeCommand command, Model model) {
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedRecipe.getId() + "/show/";
    }

    @GetMapping("recipe/{id}/update")
    public String update(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }


    @GetMapping("recipe/{id}/delete")
    public String delete(@PathVariable String id) {
        log.debug("Deleting id: " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
