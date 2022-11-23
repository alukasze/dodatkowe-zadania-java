package legacyfigher.dietary.newproducts;

public class Counter {

    private final Integer counter;

    private Counter(Integer counter) {
        this.counter = counter;
    }

    public static Counter of(Integer counter) {
        if (counter == null) {
            throw new IllegalStateException("null counter");
        }
        if (counter < 0) {
            throw new IllegalStateException("Negative counter");
        }
        return new Counter(counter);
    }

    public boolean isNotZero() {
        return counter > 0;
    }

    public Counter decrement() {
        return Counter.of(counter - 1);
    }

    public Counter increment() {
        return Counter.of(counter + 1);
    }

    public Integer toInteger() {
        return counter;
    }
}
