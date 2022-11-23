package legacyfigher.dietary.newproducts;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OldProductTest {

    @Test
    void validatedCanDecrementCounter() {
        // expect
        assertThatThrownBy(() -> newOldProduct(null, 10).decrementCounter())
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> newOldProduct(new BigDecimal(-1), 10).decrementCounter())
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> newOldProduct(BigDecimal.ONE, null).decrementCounter())
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> newOldProduct(BigDecimal.ONE, 0).decrementCounter())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void decrementsCounter() {
        // given
        OldProduct oldProduct = newOldProduct(BigDecimal.TEN, 1);
        // when
        oldProduct.decrementCounter();
        // then
        assertThat(oldProduct.getCounter()).isEqualTo(0);
    }

    @Test
    void validatedCanIncrementCounter() {
        // expect
        assertThatThrownBy(() -> newOldProduct(null, 10).incrementCounter())
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> newOldProduct(new BigDecimal(-1), 10).incrementCounter())
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> newOldProduct(BigDecimal.ONE, null).incrementCounter())
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> newOldProduct(BigDecimal.ONE, -2).incrementCounter())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void incrementsCounter() {
        // given
        OldProduct oldProduct = newOldProduct(BigDecimal.TEN, 1);
        // when
        oldProduct.incrementCounter();
        // then
        assertThat(oldProduct.getCounter()).isEqualTo(2);
    }

    @Test
    void validatesCanChangePrice() {
        // expect
        assertThatThrownBy(() -> newOldProduct(BigDecimal.ONE, null).changePriceTo(BigDecimal.TEN))
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> newOldProduct(BigDecimal.ONE, 10).changePriceTo(null))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void changesPrice() {
        // given
        OldProduct oldProduct = newOldProduct(BigDecimal.TEN, 1);
        // when
        oldProduct.changePriceTo(BigDecimal.ONE);
        // then
        assertThat(oldProduct.getPrice()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    void doesNorChangePriceIfCounterIsNotPositive() {
        // given
        OldProduct oldProduct = newOldProduct(BigDecimal.TEN, 0);
        // when
        oldProduct.changePriceTo(BigDecimal.ONE);
        // then
        assertThat(oldProduct.getPrice()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void validatesCanReplaceCharInDescription() {
        // expect
        assertThatThrownBy(() -> newOldProduct(null, "long desc").replaceCharFromDesc("a", "A"))
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> newOldProduct("", "long desc").replaceCharFromDesc("a", "A"))
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> newOldProduct("desc", null).replaceCharFromDesc("a", "A"))
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> newOldProduct("desc", "").replaceCharFromDesc("a", "A"))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void replacesCharInDescription() {
        // given
        OldProduct oldProduct = newOldProduct("ThE short dEscription", "SomE long dEsc");
        // when
        oldProduct.replaceCharFromDesc("E", "e");
        // then
        assertThat(oldProduct.getDesc()).isEqualTo("The short description");
        assertThat(oldProduct.getLongDesc()).isEqualTo("Some long desc");
    }

    @Test
    void formatsDescription() {
        // expect
        assertThat(newOldProduct("", "").formatDesc()).isEqualTo("");
        assertThat(newOldProduct((String) null, (String) null).formatDesc()).isEqualTo("");
        assertThat(newOldProduct("desc", "").formatDesc()).isEqualTo("");
        assertThat(newOldProduct(null, "longDesc").formatDesc()).isEqualTo("");
        assertThat(newOldProduct("desc", "longDesc").formatDesc()).isEqualTo("desc *** longDesc");
    }

    private OldProduct newOldProduct(BigDecimal price, Integer counter) {
        return new OldProduct(price, "sample desc", "sample long desc", counter);
    }

    private OldProduct newOldProduct(String desc, String longDesc) {
        return new OldProduct(BigDecimal.ONE, desc, longDesc, 1);
    }
}
