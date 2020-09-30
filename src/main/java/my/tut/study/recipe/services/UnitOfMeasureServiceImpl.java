package my.tut.study.recipe.services;

import my.tut.study.recipe.commands.UnitOfMeasureCommand;
import my.tut.study.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import my.tut.study.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureCommand> getAll() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
                .map(converter::convert)
                .collect(Collectors.toSet());
    }
}
