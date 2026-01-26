package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import vendingmachine.constant.ErrorMessage;

class VendingMachineTest {

    @Test
    void 금액_10원_단위_아니면_예외_발생() {
        assertThatThrownBy(() -> VendingMachine.fromMoney(5))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ErrorMessage.AMOUNT_UNIT_ERROR.getErrorMessage());
    }

    @Test
    void 구매_상품이_존재하지_않는_상품이면_예외_발생() {
        VendingMachine vendingMachine = VendingMachine.fromMoney(450);

        vendingMachine.addItems("콜라", 1500, 20);
        vendingMachine.addItems("사이다", 1000, 10);

        assertThatThrownBy(() -> vendingMachine.purchaseItem("환타"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NO_EXIST_ITEM_ERROR.getErrorMessage());
    }

    @Test
    void 구매_상품의_재고수량이_0개이면_예외_발생() {
        VendingMachine vendingMachine = VendingMachine.fromMoney(450);

        vendingMachine.addItems("콜라", 1500, 0);
        vendingMachine.addItems("사이다", 1000, 10);

        assertThatThrownBy(() -> vendingMachine.purchaseItem("콜라"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.EMPTY_STOCK_ITEM_ERROR.getErrorMessage());
    }
}