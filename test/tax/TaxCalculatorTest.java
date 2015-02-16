package tax;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class TaxCalculatorTest {

    TaxCalculator taxCalculator = new TaxCalculator(new String[]{ "exempt" });

    @Test
    public void shouldCalculateSalesTax() {
        final BigDecimal result = taxCalculator.calculateTaxForItem(new Item(new BigDecimal("14.99"), 1, "regular item"));

        assertThat(result, is(new BigDecimal("1.50")));
    }

    @Test
    public void shouldCalculateSalesTaxForTwo() {
        final BigDecimal result = taxCalculator.calculateTaxForItem(new Item(new BigDecimal("14.99"), 2, "regular item"));

        assertThat(result, is(new BigDecimal("3.00")));
    }

    @Test
    public void shouldExemptItemFromSalesTax() {
        final BigDecimal result = taxCalculator.calculateTaxForItem(new Item(new BigDecimal("14.99"), 1, "exempt"));

        assertThat(result, is(new BigDecimal("0.00")));
    }

    @Test
    public void shouldApplyImportTaxInAdditionToSalesTax() {
        final BigDecimal result = taxCalculator.calculateTaxForItem(new Item(new BigDecimal("47.50"), 1, "imported regular item"));

        assertThat(result, is(new BigDecimal("7.15")));
    }

    @Test
    public void shouldApplyImportTaxToItemExemptFromSalesTax() {
        final BigDecimal result = taxCalculator.calculateTaxForItem(new Item(new BigDecimal("10.00"), 1, "imported exempt item"));

        assertThat(result, is(new BigDecimal("0.50")));
    }
}
