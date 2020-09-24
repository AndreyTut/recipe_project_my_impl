package my.tut.study.recipe.converters;

import lombok.Synchronized;
import my.tut.study.recipe.commands.NotesCommand;
import my.tut.study.recipe.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand notesCommand) {
        if (notesCommand != null) {
            Notes notes = new Notes();
            notes.setId(notesCommand.getId());
            notes.setRecipeNotes(notesCommand.getRecipeNotes());
            return notes;
        }
        return null;
    }
}
