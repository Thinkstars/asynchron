package eu.thinkstars.asynchron.data;

import eu.thinkstars.asynchron.enums.CalculationTyp;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String expenseName;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private CalculationTyp expenseType;

    @Column
    private BigDecimal amount;

    @Column
    private BigDecimal millage;

    @Column
    private BigDecimal dayRange;

    @Column
    private String comment;
}
