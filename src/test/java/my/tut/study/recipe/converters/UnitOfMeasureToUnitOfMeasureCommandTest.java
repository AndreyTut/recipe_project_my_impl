package my.tut.study.recipe.converters;

import my.tut.study.recipe.commands.UnitOfMeasureCommand;
import my.tut.study.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private UnitOfMeasureToUnitOfMeasureCommand converter;
    private final Long ID = 1002L;
    private final String DESCRIPTION = "Description";

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        assertNotNull(converter.convert(unitOfMeasure));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(ID);
        unitOfMeasure.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);

        //then
        assertNotNull(unitOfMeasureCommand);
        assertEquals(ID, unitOfMeasureCommand.getId());
        assertEquals(DESCRIPTION, unitOfMeasureCommand.getDescription());
    }
}