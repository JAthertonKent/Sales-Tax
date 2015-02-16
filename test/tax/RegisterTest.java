package tax;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class RegisterTest {

    private Register register;

    @Before
    public void setUp() throws Exception {
        register = new Register();
    }

    @Test
    public void shouldCalculateTaxAndTotal() {
        final String receiptItem = register.addItem("1 music CD at 14.99");

        assertThat(receiptItem, is("1 music CD: 16.49"));
        assertThat(register.calculateTax(), is(new BigDecimal("1.50")));
        assertThat(register.calculateTotal(), is(new BigDecimal("16.49")));
    }

    @Test
    public void shouldCalculateTaxOfMultipleItems() {
        register.addItem("1 music CD at 14.99");
        register.addItem("1 bottle of perfume at 18.99");

        assertThat(register.calculateTax(), is(new BigDecimal("3.40")));
        assertThat(register.calculateTotal(), is(new BigDecimal("37.38")));
    }

    @Test
    public void shouldExemptItems() {
        register.addItem("1 book at 12.49");

        assertThat(register.calculateTax(), is(new BigDecimal("0.00")));
        assertThat(register.calculateTotal(), is(new BigDecimal("12.49")));
    }

}