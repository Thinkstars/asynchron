package eu.thinkstars.asynchron.services;

import eu.thinkstars.asynchron.domain.ExpenseDto;
import eu.thinkstars.asynchron.repositories.ExpensesRepository;
import eu.thinkstars.asynchron.utils.ExpenseMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final ExpenseMapper expenseMapper = Mappers.getMapper(ExpenseMapper.class);

    public ExpensesService(final ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    public List<ExpenseDto> fetchAllExpenses() {
        List<ExpenseDto> expenses = expenseMapper.etitiesToDtos(expensesRepository.findAll());
        Collections.sort(expenses);

        return expenses;
    }

    public ExpenseDto fetchExpense(final Long expenseId) {
        return expenseMapper.entityToDto(expensesRepository.findById(expenseId).orElse(null));
    }

    public ExpenseDto persistExpense(final ExpenseDto expenseDto) {
        return expenseMapper.entityToDto(expensesRepository.save(expenseMapper.dtoToEntity(expenseDto)));
    }

    public void dropExpense(final Long id) {
        expensesRepository.deleteById(id);
    }
}
