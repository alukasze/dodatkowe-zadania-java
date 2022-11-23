package legacyfigher.dietary.newproducts;

import java.math.BigDecimal;

public class Price {

    private final BigDecimal price;

    private Price(BigDecimal price) {
        this.price = price;
    }

    public static Price of(BigDecimal price) {
        if (price == null || price.signum() <= 0) {
            throw new IllegalStateException("Invalid price");
        }
        return new Price(price);
    }

    public BigDecimal toBigDecimal() {
        return price;
    }
}
