package my.tut.study.recipe.converters;

import my.tut.study.recipe.commands.IngredientCommand;
import my.tut.study.recipe.commands.UnitOfMeasureCommand;
import my.tut.study.recipe.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private static final Long ID = 10007L;
    private static final String DESCRIPTION = "Description";
    private static BigDecimal amount = new BigDecimal(100);
    private static final Long UOM_ID = 10008L;
    private static final String UOM_DESCRIPTION = "uom description";
    private IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(amount);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        unitOfMeasureCommand.setDescription(UOM_DESCRIPTION);
        ingredientCommand.setUnitOfMeasure(unitOfMeasureCommand);

        //when
        Ingredient ingredient = converter.convert(ingredientCommand);

        //then
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(amount, ingredientCommand.getAmount());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
        assertEquals(UOM_DESCRIPTION, ingredient.getUnitOfMeasure().getDescription());
    }
}