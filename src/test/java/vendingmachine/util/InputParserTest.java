package vendingmachine.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import vendingmachine.constant.ErrorMessage;

class InputParserTest {

    @Test
    void 금액이_숫자가_아니면_예외_발생() {
        assertThatThrownBy(() -> InputParser.parseMachineMoney("a"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ErrorMessage.AMOUNT_NUMBER_FORMAT_ERROR.getErrorMessage());
    }

    @Test
    void 상품입력_올바른_형식이_아니면_예외_발생() {
        assertThatThrownBy(() -> InputParser.parseMachineItems("[사이다 100 10],[콜라 100 10]"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INPUT_FORMAT_ERROR.getErrorMessage());
    }

    @Test
    void 상품명_중복이면_예외_발생() {
        assertThatThrownBy(() -> InputParser.parseMachineItems("[사이다,100,10];[사이다,100,10]"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.ITEM_NAME_UNIQUE_ERROR.getErrorMessage());
    }
}