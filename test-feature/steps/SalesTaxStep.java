package steps;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import tax.Register;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;

public class SalesTaxStep {

    private Register register;
    private StringBuilder receipt;

    @Step("Register Items <file>")
    public void registerItems(String file) {
        register = new Register();
        receipt = new StringBuilder();

        final Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            receipt.append(register.addItem(scanner.nextLine()));
        }
    }

    @Step("Itemized receipt should contain <table>")
    public void itemizedReceiptShouldContain(Table table) {
        final List<List<String>> receiptItems = table.getRows();
        for (List<String> receiptItem : receiptItems) {
            final String item = receiptItem.get(0);
            assertTrue(receipt.toString().contains(item));
        }
    }

    @Step("Total sales tax is <totalTax>")
    public void salesTaxIs(String totalTax) {
        assertThat(new BigDecimal(totalTax), is(register.calculateTax()));
    }


    @Step("Total is <total>")
    public void totalIs(String total) {
        assertThat(new BigDecimal(total), is(register.calculateTotal()));
    }
}
