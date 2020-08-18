package my.tut.study.recipe.services;

import lombok.extern.slf4j.Slf4j;
import my.tut.study.recipe.domain.Recipe;
import my.tut.study.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    private final RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("inside RecipeService");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }
}
