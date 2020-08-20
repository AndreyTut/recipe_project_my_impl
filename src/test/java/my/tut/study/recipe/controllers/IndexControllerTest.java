package my.tut.study.recipe.controllers;

import my.tut.study.recipe.domain.Recipe;
import my.tut.study.recipe.services.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class IndexControllerTest {

    private IndexController controller;
    @Mock
    private RecipeService recipeService;
    @Mock
    private Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        //given
        HashSet<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        recipes.iterator().next().setId(100L);
        recipes.add(new Recipe());

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        //when
        String page = controller.getIndexPage(model);
        //then
        Assert.assertEquals("index", page);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Assert.assertEquals(2, argumentCaptor.getValue().size());
    }
}