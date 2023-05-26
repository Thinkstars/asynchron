package eu.thinkstars.asynchron.services;

import eu.thinkstars.asynchron.enums.CalculationTyp;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ExpenseCalculatorService {

    /**
     * Service method to calculate amount of per Diems or deductions for mileage.
     * Calculating of per Diem:
     * multiplicator (amount of days in business tripp) * calculation factor (euro per day)
     * Calculating of deductions for mileage:
     * multiplicator (amount miles or km) * calculation factor (cent per mile or km)
     *
     * @return calculated amount
     */
    public BigDecimal calculateAmount(final BigDecimal multiplicator, String calculationTyp) {
        return multiplicator.multiply(CalculationTyp.valueOf(calculationTyp).getCalculationFactor()).setScale(2, RoundingMode.HALF_UP);
    }
}
