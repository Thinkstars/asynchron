package eu.thinkstars.asynchron.services;

import eu.thinkstars.asynchron.AbstractApplicationTest;
import eu.thinkstars.asynchron.data.Expense;
import eu.thinkstars.asynchron.domain.ExpenseDto;
import eu.thinkstars.asynchron.enums.CalculationTyp;
import eu.thinkstars.asynchron.repositories.ExpensesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpensesServiceTest extends AbstractApplicationTest {

    @Mock
    private ExpensesRepository expensesRepository;

    private Expense expense1;

    private ExpenseDto expenseDto1;

    @InjectMocks
    private ExpensesService expensesService;

    @BeforeEach
    void setUp() {

        expense1 = Expense.builder()
                .id(1L)
                .build();

        expenseDto1 = ExpenseDto.builder()
                .id(1L)
                .build();
    }

    @Test
    void fetchAllExpenses() {
        final var expense1 = Expense.builder()
                .expenseName("Entfernungspauschale")
                .expenseType(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)
                .millage(new BigDecimal(125))
                .dateFrom(Date.valueOf(LocalDate.now()))
                .dateTo(Date.valueOf(LocalDate.now()))
                .build();
        final var expense2 = Expense.builder()
                .expenseName("Entfernungspauschale")
                .expenseType(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)
                .dateFrom(Date.valueOf(LocalDate.now()))
                .dateTo(Date.valueOf(LocalDate.now()))
                .millage(new BigDecimal(125)).build();
        when(expensesRepository.findAll()).thenReturn(Arrays.asList(expense1, expense2));

        List<ExpenseDto> expenses = expensesService.fetchAllExpenses();

        assertEquals(2, expenses.size());
    }

    @Test
    void fetchExpense() {
        final var expense = Expense.builder()
                .expenseName("Entfernungspauschale")
                .expenseType(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)
                .millage(new BigDecimal(125))
                .dateFrom(Date.valueOf(LocalDate.now()))
                .dateTo(Date.valueOf(LocalDate.now()))
                .build();
        when(expensesRepository.findById(Long.valueOf("1"))).thenReturn(Optional.of(expense));

        ExpenseDto expenseDto = expensesService.fetchExpense(Long.valueOf("1"));

        assertEquals("Entfernungspauschale", readPropertyAsString(expenseDto.getExpenseType().getType()));
    }

    @Test
    void persistExpense() {
        final var expenseDto = ExpenseDto.builder()
                .expenseName("Entfernungspauschale")
                .expenseType(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)
                .millage(new BigDecimal(125))
                .dateFrom(Date.valueOf(LocalDate.now()))
                .dateTo(Date.valueOf(LocalDate.now()))
                .build();
        final var expense = Expense.builder()
                .id(Long.valueOf("1"))
                .expenseName("Entfernungspauschale")
                .expenseType(CalculationTyp.DEDUCTIONS_FOR_MILEAGE)
                .millage(new BigDecimal(125))
                .dateFrom(Date.valueOf(LocalDate.now()))
                .dateTo(Date.valueOf(LocalDate.now()))
                .build();

        when(expensesRepository.save(any(Expense.class))).thenReturn(expense);

        ExpenseDto expenseDto1 = expensesService.persistExpense(expenseDto);

        assertEquals(1L, expenseDto1.getId());
    }

    @Test
    void testFetchAllExpenses() {
        var expanses = List.of(expense1, Expense.builder().build());
        var expanseDtos = List.of(expenseDto1, ExpenseDto.builder().build());
        when(expensesRepository.findAll()).thenReturn(expanses);
        //when(expensesRepository.findAll()).thenReturn()
        var result = expensesService.fetchAllExpenses();
        assertIterableEquals(expanseDtos, result);


    }

    @Test
    void testFetchExpense() {
    }

    @Test
    void testPersistExpense() {
        when(expensesRepository.save(expense1)).thenReturn(expense1);
        var result = expensesService.persistExpense(expenseDto1);
        assertEquals(result, expenseDto1);

    }

    @Test
    void dropExpense() {
        expensesService.dropExpense(1L);
        verify(expensesRepository, times(1)).deleteById(1L);
    }
}
