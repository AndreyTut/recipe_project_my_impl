package my.tut.study.recipe.services;

import my.tut.study.recipe.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> getAll();
}
