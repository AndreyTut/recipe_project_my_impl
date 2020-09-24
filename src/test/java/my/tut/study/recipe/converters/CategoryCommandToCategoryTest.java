package my.tut.study.recipe.converters;

import my.tut.study.recipe.commands.CategoryCommand;
import my.tut.study.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private CategoryCommandToCategory converter;

    private static final String DESCRIPTION = "Description";
    private static final Long ID = 1001L;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        Category category = converter.convert(null);
        assertNull(category);
    }

    @Test
    public void testEmptyObject() {
        CategoryCommand categoryCommand = new CategoryCommand();
        Category category = converter.convert(categoryCommand);
        assertNotNull(category);
    }

    @Test
    public void convert() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(categoryCommand);

        //then
        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}