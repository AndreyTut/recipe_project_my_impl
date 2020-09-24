package my.tut.study.recipe.converters;

import com.sun.istack.NotNull;
import lombok.Synchronized;
import my.tut.study.recipe.commands.RecipeCommand;
import my.tut.study.recipe.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryCommandToCategory;
    private final NotesCommandToNotes notesCommandToNotes;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandToCategory,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 NotesCommandToNotes notesCommandToNotes
    ) {
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.notesCommandToNotes = notesCommandToNotes;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }


    @Synchronized
    @NotNull
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {

        if (recipeCommand != null) {
            Recipe recipe = new Recipe();

            recipe.setId(recipeCommand.getId());
            recipe.setDescription(recipeCommand.getDescription());
            recipe.setPrepTime(recipeCommand.getPrepTime());
            recipe.setCookTime(recipeCommand.getCookTime());
            recipe.setServings(recipeCommand.getServings());
            recipe.setSource(recipeCommand.getSource());
            recipe.setUrl(recipeCommand.getUrl());
            recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotes()));
            recipe.setDifficulty(recipeCommand.getDifficulty());
            recipe.setDirections(recipeCommand.getDirections());

            if (recipeCommand.getCategories() != null) {
                recipeCommand.getCategories()
                        .forEach(categoryCommand -> recipe.getCategories().add(categoryCommandToCategory.convert(categoryCommand)));
            }

            if (recipeCommand.getIngredients() != null) {
                recipeCommand.getIngredients()
                        .forEach(ingredientCommand -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredientCommand)));
            }

            return recipe;
        }

        return null;
    }
}
