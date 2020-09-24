package my.tut.study.recipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.tut.study.recipe.converters.CategoryToCategoryCommand;
import my.tut.study.recipe.domain.Difficulty;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private String directions;
}
