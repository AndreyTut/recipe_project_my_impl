package my.tut.study.recipe.converters;

import lombok.Synchronized;
import my.tut.study.recipe.commands.CategoryCommand;
import my.tut.study.recipe.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if (category != null) {
            CategoryCommand categoryCommand = new CategoryCommand();
            categoryCommand.setId(category.getId());
            categoryCommand.setDescription(category.getDescription());
            return categoryCommand;
        }
        return null;
    }
}
