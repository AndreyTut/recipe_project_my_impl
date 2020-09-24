package my.tut.study.recipe.converters;

import lombok.Synchronized;
import my.tut.study.recipe.commands.UnitOfMeasureCommand;
import my.tut.study.recipe.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure != null) {
            UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
            unitOfMeasureCommand.setId(unitOfMeasure.getId());
            unitOfMeasureCommand.setDescription(unitOfMeasure.getDescription());
            return unitOfMeasureCommand;
        }
        return null;
    }
}
