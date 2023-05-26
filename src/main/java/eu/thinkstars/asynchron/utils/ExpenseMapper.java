package eu.thinkstars.asynchron.utils;

import eu.thinkstars.asynchron.data.Expense;
import eu.thinkstars.asynchron.domain.ExpenseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ExpenseMapper {

    ExpenseDto entityToDto(Expense expense);

    Expense dtoToEntity(final ExpenseDto expenseDto);

    List<ExpenseDto> etitiesToDtos(final List<Expense> expenses);

    List<Expense> dtosToEntities(final List<ExpenseDto> expenses);
}
