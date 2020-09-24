package my.tut.study.recipe.converters;

import my.tut.study.recipe.commands.CategoryCommand;
import my.tut.study.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    CategoryToCategoryCommand converter;

    private static final String DESCRIPTION = "Description";
    private static final Long ID = 1001L;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        //given
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertEquals(ID, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}