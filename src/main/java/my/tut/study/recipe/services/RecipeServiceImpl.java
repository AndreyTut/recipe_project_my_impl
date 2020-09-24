package my.tut.study.recipe.services;

import lombok.extern.slf4j.Slf4j;
import my.tut.study.recipe.commands.RecipeCommand;
import my.tut.study.recipe.converters.RecipeCommandToRecipe;
import my.tut.study.recipe.converters.RecipeToRecipeCommand;
import my.tut.study.recipe.domain.Recipe;
import my.tut.study.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe, RecipeRepository recipeRepository) {
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeRepository = recipeRepository;
    }

    private final RecipeRepository recipeRepository;

//    @Override
//    public Set<RecipeCommand> getRecipes() {
//        log.debug("inside RecipeService");
//        Set<RecipeCommand> recipes = new HashSet<>();
//        recipeRepository.findAll().forEach(recipe -> recipes.add(recipeToRecipeCommand.convert(recipe)));
//        return recipes;
//    }
//
//    @Override
//    public RecipeCommand findById(Long id) {
//        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
//        Recipe recipe = optionalRecipe.orElseThrow(() -> new RuntimeException("not found"));
//        return recipeToRecipeCommand.convert(recipe);
//    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }

        return recipeOptional.get();
    }

    @Transactional
    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe recipe = recipeCommandToRecipe.convert(command);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
