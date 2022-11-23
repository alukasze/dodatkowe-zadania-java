package legacyfigher.dietary.newproducts;

import java.math.BigDecimal;
import java.util.UUID;

public class OldProduct {

    private UUID serialNumber = UUID.randomUUID();

    private BigDecimal price;
    private String desc;

    private String longDesc;

    private Integer counter;

    public OldProduct(Price price, Description description, Counter counter) {
        this.price = price.toBigDecimal();
        this.desc = description.getShortDesc();
        this.longDesc = description.getLongDesc();
        this.counter = counter.toInteger();
    }

    @Deprecated
    public OldProduct(BigDecimal price, String desc, String longDesc, Integer counter) {
        this.price = price;
        this.desc = desc;
        this.longDesc = longDesc;
        this.counter = counter;
    }

    void decrementCounter() {
        Price.of(price);
        counter = Counter.of(counter).decrement().toInteger();
    }

    void incrementCounter() {
        Price.of(price);
        counter = Counter.of(counter).increment().toInteger();
    }

    void changePriceTo(BigDecimal newPrice) {
        if (Counter.of(counter).isNotZero()) {
            if (newPrice == null) {
                throw new IllegalStateException("new price null");
            }
            this.price = newPrice;  // TODO validate newPrice > 0, before implement it make sure that you can
        }
    }

    void replaceCharFromDesc(String charToReplace, String replaceWith) {
        Description description = Description.of(desc, longDesc)
                .replaceCharFromDesc(charToReplace, replaceWith);
        desc = description.getShortDesc();
        longDesc = description.getLongDesc();
    }

    String formatDesc() {
        return Description.withoutValidation(desc, longDesc).format();
    }

    BigDecimal getPrice() {
        return price;
    }

    String getDesc() {
        return desc;
    }

    String getLongDesc() {
        return longDesc;
    }

    Integer getCounter() {
        return counter;
    }
}
