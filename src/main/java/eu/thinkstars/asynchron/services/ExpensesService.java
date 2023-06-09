package eu.thinkstars.asynchron.services;

import eu.thinkstars.asynchron.domain.ExpenseDto;
import eu.thinkstars.asynchron.enums.CalculationTyp;
import eu.thinkstars.asynchron.repositories.ExpensesRepository;
import eu.thinkstars.asynchron.utils.ExpenseMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        expenses.sort(createExpenseComparator());

        return expenses;
    }

    public ExpenseDto fetchExpense(final Long expenseId) {
        return expenseMapper.entityToDto(expensesRepository.findById(expenseId).orElse(null));
    }

    public ExpenseDto persistExpense(final ExpenseDto expenseDto) {
        if (expenseDto.getExpenseType().equals(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)) {
            expenseDto.setDateTo(expenseDto.getDateFrom());
        }
        return expenseMapper.entityToDto(expensesRepository.save(expenseMapper.dtoToEntity(expenseDto)));
    }

    public void dropExpense(final Long id) {
        expensesRepository.deleteById(id);
    }

    private static Comparator<ExpenseDto> createExpenseComparator() {
        return Comparator.comparing(ExpenseDto::getDateFrom).thenComparing(ExpenseDto::getDateTo);
    }
}
