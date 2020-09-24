package my.tut.study.recipe.converters;

import my.tut.study.recipe.commands.NotesCommand;
import my.tut.study.recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private NotesCommandToNotes converter;
    private final Long ID = 10004L;
    private final String RECIPE_NOTES = "recipe notes";

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}