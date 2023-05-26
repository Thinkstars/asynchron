package eu.thinkstars.asynchron.enums;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

public enum CalculationTyp {

    PER_DIEMS("APPLICATION.CALCULATION_TYPE.PERDIEMS", new BigDecimal(28)),
    DEDUCTIONS_FOR_MILEAGE("APPLICATION.CALCULATION_TYPE.MILEAGE", new BigDecimal("0.3"));

    private final String type;
    private final BigDecimal calculationFactor;

    CalculationTyp(String calculationTyp, BigDecimal calculationFactor) {
        this.type = calculationTyp;
        this.calculationFactor = calculationFactor;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getCalculationFactor() {
        return calculationFactor;
    }

    public static Collection<CalculationTyp> allAvailableCalculationTypes() {
        return Arrays.asList(PER_DIEMS, DEDUCTIONS_FOR_MILEAGE);
    }
}
