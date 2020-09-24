package my.tut.study.recipe.converters;

import my.tut.study.recipe.commands.UnitOfMeasureCommand;
import my.tut.study.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private UnitOfMeasureCommandToUnitOfMeasure converter;
    private final Long ID = 1002L;
    private final String DESCRIPTION = "Description";

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        assertNotNull(converter.convert(unitOfMeasureCommand));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID);
        unitOfMeasureCommand.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure unitOfMeasure = converter.convert(unitOfMeasureCommand);

        //then
        assertNotNull(unitOfMeasure);
        assertEquals(ID, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }
}