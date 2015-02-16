package tax;

import java.math.BigDecimal;

public class Item {

    private final BigDecimal amount;
    private final int quantity;
    private final String description;

    public Item(BigDecimal amount, int quantity, String description) {
        this.amount = amount;
        this.quantity = quantity;
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal priceForQuantity() {
        return amount.multiply(new BigDecimal(quantity));
    }

    @Override
    public String toString() {
        return "Item{" +
            "amount=" + amount +
            ", quantity=" + quantity +
            ", description='" + description + '\'' +
            '}';
    }
}
