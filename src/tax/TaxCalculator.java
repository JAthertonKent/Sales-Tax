package tax;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.BigDecimal.ROUND_UP;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

public class TaxCalculator {

    public static final BigDecimal SALES_TAX = new BigDecimal(.10);
    public static final BigDecimal IMPORT_TAX = new BigDecimal(.05);
    private String[] exemptItems;

    public TaxCalculator(String[] exemptItems) {
        this.exemptItems = exemptItems;
    }

    public BigDecimal calculateTaxForItem(Item item) {
        BigDecimal totalTax = calculateSalesTax(item);

        if (item.getDescription().contains("imported")) {
            totalTax = totalTax.add(item.priceForQuantity().multiply(IMPORT_TAX));
        }

        return convertToCents(roundToFiveCents(totalTax));
    }

    private BigDecimal roundToFiveCents(BigDecimal value) {
        final BigDecimal noRiffRaff = value.setScale(3, ROUND_HALF_UP);
        return noRiffRaff.multiply(new BigDecimal(20)).setScale(0, ROUND_UP).divide(new BigDecimal(20));
    }

    private BigDecimal calculateSalesTax(Item item) {
        for(String exempt: exemptItems) {
            if (item.getDescription().contains(exempt)) {
                return ZERO;
            }
        }
        return item.priceForQuantity().multiply(SALES_TAX);
    }

    private BigDecimal convertToCents(BigDecimal amount) {
        return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
