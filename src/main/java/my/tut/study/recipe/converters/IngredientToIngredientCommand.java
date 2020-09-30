package my.tut.study.recipe.converters;

import lombok.Synchronized;
import my.tut.study.recipe.commands.IngredientCommand;
import my.tut.study.recipe.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient != null) {
            IngredientCommand ingredientCommand = new IngredientCommand();
            ingredientCommand.setId(ingredient.getId());
            ingredientCommand.setDescription(ingredient.getDescription());
            ingredientCommand.setAmount(ingredient.getAmount());
            ingredientCommand.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
            if (ingredient.getRecipe() != null) {
                ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
            }
            return ingredientCommand;
        }
        return null;
    }
}
