package my.tut.study.recipe.services;

import my.tut.study.recipe.commands.RecipeCommand;
import my.tut.study.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
