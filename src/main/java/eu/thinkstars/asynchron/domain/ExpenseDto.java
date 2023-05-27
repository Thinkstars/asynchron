package eu.thinkstars.asynchron.domain;

import eu.thinkstars.asynchron.enums.CalculationTyp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExpenseDto implements Comparable<ExpenseDto> {

    private Long id;
    private String expenseName;
    private CalculationTyp expenseType;
    private BigDecimal amount;
    private BigDecimal millage;
    private BigDecimal dayRange;
    private String comment;
    private Date dateFrom;
    private Date dateTo;

    public ExpenseDto() {
        this.dateFrom = Date.valueOf(LocalDate.now());
        this.dateTo = Date.valueOf(LocalDate.now());
    }

    public String showDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return CalculationTyp.DEDUCTIONS_FOR_MILEAGE.equals(expenseType) ? simpleDateFormat.format(dateFrom) : simpleDateFormat.format(dateFrom) + " - " + simpleDateFormat.format(dateTo);
    }


    @Override
    public int compareTo(ExpenseDto o) {
        return showDate().compareTo(o.showDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseDto that = (ExpenseDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(expenseName, that.expenseName) &&
                expenseType == that.expenseType &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(millage, that.millage) &&
                Objects.equals(dayRange, that.dayRange) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(dateFrom, that.dateFrom) &&
                Objects.equals(dateTo, that.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, expenseName, expenseType, amount, millage, dayRange, comment, dateFrom, dateTo);
    }
}
