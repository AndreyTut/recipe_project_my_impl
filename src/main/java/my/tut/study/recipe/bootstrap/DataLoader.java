package my.tut.study.recipe.bootstrap;

import my.tut.study.recipe.domain.*;
import my.tut.study.recipe.repositories.CategoryRepository;
import my.tut.study.recipe.repositories.RecipeRepository;
import my.tut.study.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Recipe recipeGuacamole = new Recipe();
        recipeGuacamole.setDescription("How to Make Perfect Guacamole Recipe");
        recipeGuacamole.setPrepTime(10);
        recipeGuacamole.setServings(4);
        recipeGuacamole.setSource("https://www.simplyrecipes.com/");
        recipeGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipeGuacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
        Notes notes = new Notes();
        notes.setRecipe(recipeGuacamole);
        notes.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");
        recipeGuacamole.setNotes(notes);
        recipeGuacamole.setCategories(Collections.singleton(categoryRepository.findByDescription("Mexican").orElse(null)));

        Ingredient avocados = new Ingredient();
        avocados.setDescription("ripe avocados");
        avocados.setAmount(new BigDecimal(2));

        Ingredient salt = new Ingredient();
        salt.setDescription("salt");
        salt.setAmount(new BigDecimal(0.25));
        salt.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Teaspoon").orElse(null));

        Ingredient juice = new Ingredient();
        juice.setDescription("fresh lime juice or lemon juice");
        juice.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").orElse(null));
        juice.setAmount(new BigDecimal(1));
        //       juice.setRecipe(recipeGuacamole);
        Set<Ingredient> guacIngrs = new HashSet<>();
        guacIngrs.add(avocados);
        guacIngrs.add(salt);
        guacIngrs.add(juice);

        guacIngrs.forEach(ingredient -> ingredient.setRecipe(recipeGuacamole));

        recipeGuacamole.setIngredients(guacIngrs);
        recipeGuacamole.setDifficulty(Difficulty.EASY);
        System.out.println("Saving recipe...................................................");
        recipeRepository.save(recipeGuacamole);


        // Next Recipe

        Recipe recipeTacos = new Recipe();
        recipeTacos.setDescription("Spicy Grilled Chicken Tacos Recipe");
        recipeTacos.setPrepTime(20);
        recipeTacos.setCookTime(15);
        recipeTacos.setSource("https://www.simplyrecipes.com/");
        recipeTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        recipeTacos.setServings(6);
        recipeTacos.setDirections(
                "1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                        "\n" +
                        "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                        "\n" +
                        "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                        "\n" +
                        "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                        "\n" +
                        "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                        "\n" +
                        "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                        "\n" +
                        "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        Notes tacosNotes = new Notes();
        tacosNotes.setRecipe(recipeTacos);
        tacosNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        recipeTacos.setNotes(tacosNotes);

        Category dinnerCat = categoryRepository.findByDescription("Dinner").orElse(null);
        Category grillCat = categoryRepository.findByDescription("Grill").orElse(null);
        Category chickenCat = categoryRepository.findByDescription("Chicken").orElse(null);

        Set<Category> tacoCategorySet = new HashSet<>();
        tacoCategorySet.add(dinnerCat);
        tacoCategorySet.add(chickenCat);
        tacoCategorySet.add(grillCat);
        recipeTacos.setCategories(tacoCategorySet);

        Ingredient tacoIng1 = new Ingredient();
        tacoIng1.setAmount(new BigDecimal(2));
        tacoIng1.setDescription("ancho chili powder");
        tacoIng1.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").orElse(null));

        Ingredient tacoIng2 = new Ingredient();
        tacoIng2.setAmount(new BigDecimal(1));
        tacoIng2.setDescription("dried oregano");
        tacoIng2.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Teaspoon").orElse(null));

        Ingredient tacoIng3 = new Ingredient();
        tacoIng3.setAmount(new BigDecimal(1));
        tacoIng3.setDescription("dried cumin");
        tacoIng3.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Teaspoon").orElse(null));

        Set<Ingredient> tacoInrgSet = new HashSet<>();
        tacoInrgSet.add(tacoIng1);
        tacoInrgSet.add(tacoIng2);
        tacoInrgSet.add(tacoIng3);
        tacoInrgSet.forEach(ingredient -> ingredient.setRecipe(recipeTacos));

        recipeTacos.setIngredients(tacoInrgSet);
        recipeTacos.setDifficulty(Difficulty.EASY);
        recipeRepository.save(recipeTacos);

        Recipe savedRecipe = recipeRepository.findAll().iterator().next();
        System.out.println("id============" + savedRecipe.getId());
        Set<Ingredient> savedIngredients = savedRecipe.getIngredients();
        if (savedIngredients != null) {
            savedIngredients.forEach(ingredient -> {
                System.out.println(ingredient.getDescription());
                System.out.println(ingredient.getRecipe().getId());
            });
        }

    }
}
