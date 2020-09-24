package my.tut.study.recipe.converters;

import lombok.Synchronized;
import my.tut.study.recipe.commands.UnitOfMeasureCommand;
import my.tut.study.recipe.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if (unitOfMeasureCommand != null) {
            UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
            unitOfMeasure.setId(unitOfMeasureCommand.getId());
            unitOfMeasure.setDescription(unitOfMeasureCommand.getDescription());
            return unitOfMeasure;
        }
        return null;
    }
}
