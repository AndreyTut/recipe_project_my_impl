package my.tut.study.recipe.services;

import my.tut.study.recipe.commands.IngredientCommand;
import my.tut.study.recipe.commands.UnitOfMeasureCommand;
import my.tut.study.recipe.converters.IngredientCommandToIngredient;
import my.tut.study.recipe.converters.IngredientToIngredientCommand;
import my.tut.study.recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import my.tut.study.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import my.tut.study.recipe.domain.Ingredient;
import my.tut.study.recipe.domain.Recipe;
import my.tut.study.recipe.domain.UnitOfMeasure;
import my.tut.study.recipe.repositories.RecipeRepository;
import my.tut.study.recipe.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientToIngredientCommand ingredientToIngredientCommand;

    IngredientCommandToIngredient ingredientCommandToIngredient;

    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand, ingredientCommandToIngredient, unitOfMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        Recipe recipe = new Recipe();
        recipe.setId(4L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        IngredientCommand command = ingredientService.findByRecipeIdAndIngredientId(4L, 1L);

        assertEquals(Long.valueOf(1L), command.getId());
        assertEquals(Long.valueOf(4L), command.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void save() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);
        command.setDescription("description");
        Recipe savedRecipe = new Recipe();
        UnitOfMeasureCommand unitOfMeasure = new UnitOfMeasureCommand();
        unitOfMeasure.setId(4L);
        command.setUnitOfMeasure(unitOfMeasure);

        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        Optional<Recipe> recipeOptional = Optional.of(savedRecipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);
        when(unitOfMeasureRepository.findById(anyLong())).thenReturn(Optional.of(new UnitOfMeasure()));

        //when
        IngredientCommand savedCommand = ingredientService.save(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));

    }

    @Test
    public void deleteById() {
        //given
        Recipe recipe = new Recipe();
        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        recipe.setId(1L);
        ingredient1.setId(2L);
        ingredient2.setId(3L);
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        when(recipeRepository.save(recipe)).thenReturn(recipe);

        //when
        ingredientService.deleteByRecipeIdAndIngredientId(1L, 2L);

        //then

        assertEquals(1, recipe.getIngredients().size());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}