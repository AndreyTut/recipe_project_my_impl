package my.tut.study.recipe.converters;

import lombok.Synchronized;
import my.tut.study.recipe.commands.CategoryCommand;
import my.tut.study.recipe.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (categoryCommand != null) {
            Category category = new Category();
            category.setId(categoryCommand.getId());
            category.setDescription(categoryCommand.getDescription());
            return category;
        }
        return null;
    }
}
