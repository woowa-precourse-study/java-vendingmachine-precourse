package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import vendingmachine.constant.ErrorMessage;

class InsertedMoneyTest {

    @Test
    void 투입_금액_10원_단위_아니면_예외_발생() {
        assertThatThrownBy(() -> InsertedMoney.fromAmount(123))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ErrorMessage.AMOUNT_UNIT_ERROR.getErrorMessage());
    }
}