package my.tut.study.recipe.converters;

import my.tut.study.recipe.commands.IngredientCommand;
import my.tut.study.recipe.domain.Ingredient;
import my.tut.study.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private IngredientToIngredientCommand converter;
    private final Long ID = 10005L;
    private final Long UOM_ID = 10006L;
    private BigDecimal amount = new BigDecimal(10);
    private UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
    private final String DESCRIPTION = "Description";
    private final String UOM_DESCRIPTION = "Description";


    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        unitOfMeasure.setDescription(UOM_DESCRIPTION);
        unitOfMeasure.setId(UOM_ID);
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convert() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(amount);
        ingredient.setUnitOfMeasure(unitOfMeasure);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(amount, ingredientCommand.getAmount());
        assertEquals(unitOfMeasure.getId(), ingredientCommand.getUnitOfMeasure().getId());
        assertEquals(unitOfMeasure.getDescription(), ingredientCommand.getUnitOfMeasure().getDescription());
    }
}