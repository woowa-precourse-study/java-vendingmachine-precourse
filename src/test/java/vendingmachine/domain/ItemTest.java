package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import vendingmachine.constant.ErrorMessage;

class ItemTest {

    @Test
    void 상품_가격_100원_미만이면_예외_발생() {
        assertThatThrownBy(() -> new Item("콜라", 50))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ErrorMessage.PRICE_MIN_ERROR.getErrorMessage());
    }

    @Test
    void 상품_가격_10원_단위_아니면_예외_발생() {
        assertThatThrownBy(() -> new Item("콜라", 123))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.PRICE_UNIT_ERROR.getErrorMessage());
    }
}