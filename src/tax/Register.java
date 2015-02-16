package tax;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Register {
    private ArrayList<Item> items = new ArrayList<Item>();
    TaxCalculator taxCalculator = new TaxCalculator(new String[]{ "book", "chocolate", "pills" });

    private BigDecimal totalTax = BigDecimal.ZERO;
    private BigDecimal totalSum = BigDecimal.ZERO;

    public String addItem(String itemString) {
        final Item item = createItem(itemString);
        items.add(item);

        BigDecimal taxForItem = taxCalculator.calculateTaxForItem(item);
        final BigDecimal totalPriceForItem = taxForItem.add(item.priceForQuantity());

        totalTax = totalTax.add(taxForItem);
        totalSum = totalSum.add(totalPriceForItem);

        return Integer.toString(item.getQuantity()) + " " + item.getDescription() + ": " + totalPriceForItem;
    }

    private Item createItem(String itemString) {
        final String[] splitLine = itemString.split(" at ");
        final String descriptionAndQuantity = splitLine[0];
        final BigDecimal amount = new BigDecimal(splitLine[1]);

        final String[] splitItemAndQuantity = descriptionAndQuantity.split(" ", 2);
        final int quantity = Integer.parseInt(splitItemAndQuantity[0]);
        final String description = splitItemAndQuantity[1];

        return new Item(amount, quantity, description);
    }

    public BigDecimal calculateTax() {
        return totalTax;
    }

    public BigDecimal calculateTotal() {
        return totalSum;
    }
}
