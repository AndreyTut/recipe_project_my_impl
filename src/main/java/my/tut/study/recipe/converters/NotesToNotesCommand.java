package my.tut.study.recipe.converters;

import lombok.Synchronized;
import my.tut.study.recipe.commands.NotesCommand;
import my.tut.study.recipe.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        if (notes != null) {
            NotesCommand notesCommand = new NotesCommand();
            notesCommand.setId(notes.getId());
            notesCommand.setRecipeNotes("recipe notes");
            return notesCommand;
        }
        return null;
    }
}
