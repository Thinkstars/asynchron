package eu.thinkstars.asynchron.services;

import eu.thinkstars.asynchron.AbstractApplicationTest;
import eu.thinkstars.asynchron.data.Expense;
import eu.thinkstars.asynchron.domain.ExpenseDto;
import eu.thinkstars.asynchron.enums.CalculationTyp;
import eu.thinkstars.asynchron.repositories.ExpensesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ExpensesServiceTest extends AbstractApplicationTest {

    @Mock
    private ExpensesRepository expensesRepository;

    @InjectMocks
    private ExpensesService expensesService;

    @Test
    void fetchAllExpenses() {
        final var expense1 = Expense.builder()
                .expenseName("Entfernungspauschale")
                .expenseTyp(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)
                .millage(new BigDecimal(125)).build();
        final var expense2 = Expense.builder()
                .expenseName("Entfernungspauschale")
                .expenseTyp(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)
                .millage(new BigDecimal(125)).build();
        Mockito.when(expensesRepository.findAll()).thenReturn(Arrays.asList(expense1, expense2));

        List<ExpenseDto> expenses = expensesService.fetchAllExpenses();

        assertEquals(2, expenses.size());
    }

    @Test
    void fetchExpense() {
        final var expense = Expense.builder()
                .expenseName("Entfernungspauschale")
                .expenseTyp(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)
                .millage(new BigDecimal(125)).build();
        Mockito.when(expensesRepository.findById(Long.valueOf("1"))).thenReturn(Optional.of(expense));

        ExpenseDto expenseDto = expensesService.fetchExpense(Long.valueOf("1"));

        assertEquals("Entfernungspauschale", readPropertyAsString(expenseDto.getExpenseTyp().getType()));
    }

    @Test
    void persistExpense() {
        final var expenseDto = ExpenseDto.builder()
                .expenseName("Entfernungspauschale")
                .expenseTyp(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)
                .millage(new BigDecimal(125)).build();
        final var expense = Expense.builder()
                .id(Long.valueOf("1"))
                .expenseName("Entfernungspauschale")
                .expenseTyp(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)
                .millage(new BigDecimal(125)).build();

        Mockito.when(expensesRepository.save(any(Expense.class))).thenReturn(expense);

        ExpenseDto expenseDto1 = expensesService.persistExpense(expenseDto);

        assertEquals(1L, expenseDto1.getId());
    }
}