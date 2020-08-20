package my.tut.study.recipe.controllers;

import my.tut.study.recipe.domain.Recipe;
import my.tut.study.recipe.services.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;

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
        Assert.assertEquals(controller.getIndexPage(model), "index");
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute("recipes", new HashSet<>());
    }
}