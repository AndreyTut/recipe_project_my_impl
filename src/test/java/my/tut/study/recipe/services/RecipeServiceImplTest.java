package my.tut.study.recipe.services;

import my.tut.study.recipe.converters.RecipeCommandToRecipe;
import my.tut.study.recipe.converters.RecipeToRecipeCommand;
import my.tut.study.recipe.domain.Recipe;
import my.tut.study.recipe.repositories.RecipeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeToRecipeCommand, recipeCommandToRecipe, recipeRepository);
    }

    @Test
    public void getRecipes() {
        HashSet<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipes);

        Assert.assertEquals(recipeService.getRecipes().size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeByIdTest() {
        Recipe recipe = new Recipe();
        long id = 1L;
        recipe.setId(id);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        assertNotNull("Null recipe returned", recipeRepository.findById(id));
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void deleteRecipeTest(){

        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        recipeService.deleteById(idToDelete);

        //no 'when', since method has void return type

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}