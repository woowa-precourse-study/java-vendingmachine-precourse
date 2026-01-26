package vendingmachine;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vendingmachine.constant.ErrorMessage;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInListTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomNumberInListTest(
            () -> {
                run("450", "[콜라,1500,20];[사이다,1000,10]", "3000", "콜라", "사이다");
                assertThat(output()).contains(
                    "자판기가 보유한 동전", "500원 - 0개", "100원 - 4개", "50원 - 1개", "10원 - 0개",
                    "투입 금액: 3000원", "투입 금액: 1500원"
                );
            },
            100, 100, 100, 100, 50
        );
    }

    @Test
    void 남은금액보다_적은_잔돈을_가지는_경우_결과_테스트() {
        assertRandomNumberInListTest(
                () -> {
                    run("450", "[콜라,1500,20];[사이다,1000,10]", "3000", "콜라", "사이다");
                    assertThat(output()).contains(
                            "자판기가 보유한 동전", "500원 - 0개", "100원 - 4개", "50원 - 1개", "10원 - 0개",
                            "투입 금액: 3000원", "투입 금액: 1500원", "잔돈\n"
                                    + "100원 - 4개\n"
                                    + "50원 - 1개"
                    );
                },
                100, 100, 100, 100, 50
        );
    }

    @Test
    void 남은금액과_같은_잔돈을_가지는_경우_결과_테스트() {
        assertRandomNumberInListTest(
                () -> {
                    run("500", "[콜라,1500,20];[사이다,1000,10]", "3000", "콜라", "사이다");
                    assertThat(output()).contains(
                            "자판기가 보유한 동전", "500원 - 0개", "100원 - 3개", "50원 - 4개", "10원 - 0개",
                            "투입 금액: 3000원", "투입 금액: 1500원", "잔돈\n"
                                    + "100원 - 3개\n"
                                    + "50원 - 4개"
                    );
                },
                100, 100, 100, 50, 50, 50, 50
        );
    }

    @Test
    void 남은금액보다_많은_잔돈을_가지는_경우_결과_테스트() {
        assertRandomNumberInListTest(
                () -> {
                    run("750", "[콜라,1500,20];[사이다,1000,10]", "3000", "콜라", "사이다");
                    assertThat(output()).contains(
                            "자판기가 보유한 동전", "500원 - 1개", "100원 - 1개", "50원 - 2개", "10원 - 5개",
                            "투입 금액: 3000원", "투입 금액: 1500원", "잔돈\n"
                                    + "500원 - 1개"
                    );
                },
                500, 100, 50, 50, 10, 10, 10, 10, 10
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(
            () -> {
                runException("-1");
                assertThat(output()).contains(ERROR_MESSAGE);
            }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", " ", "!"})
    void 자판기_보유_금액_숫자_아니면_예외_발생(String amount) {
        assertSimpleTest(
                () -> {
                    runException(amount);
                    assertThat(output()).contains(ErrorMessage.AMOUNT_NUMBER_FORMAT_ERROR.getErrorMessage());
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "15", "1234"})
    void 자판기_보유_금액_10원_단위_아니면_예외_발생(String amount) {
        assertSimpleTest(
                () -> {
                    runException(amount);
                    assertThat(output()).contains(ErrorMessage.AMOUNT_UNIT_ERROR.getErrorMessage());
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "[ ,150,10]", "[콜라,1500,20],[사이다,1000,10]"})
    void 상품명_올바른_형식_아니면_예외_발생(String input) {
        assertSimpleTest(
                () -> {
                    runException("450", input);
                    assertThat(output()).contains(ErrorMessage.INPUT_FORMAT_ERROR.getErrorMessage());
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"[콜라,1500,20];[콜라,1000,10]"})
    void 상품명_중복이면_예외_발생(String input) {
        assertSimpleTest(
                () -> {
                    runException("450", input);
                    assertThat(output()).contains(ErrorMessage.ITEM_NAME_UNIQUE_ERROR.getErrorMessage());
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"[콜라,10,20];[사이다,1000,10]"})
    void 상품가격_100원_미만이면_예외_발생(String input) {
        assertSimpleTest(
                () -> {
                    runException("450", input);
                    assertThat(output()).contains(ErrorMessage.PRICE_MIN_ERROR.getErrorMessage());
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"[콜라,123,20];[사이다,1200,10]"})
    void 상품가격_10원_단위가_아니면_예외_발생(String input) {
        assertSimpleTest(
                () -> {
                    runException("450", input);
                    assertThat(output()).contains(ErrorMessage.PRICE_UNIT_ERROR.getErrorMessage());
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "콜라"})
    void 투입금액_숫자가_아니면_예외_발생(String input) {
        assertSimpleTest(
                () -> {
                    runException("450", "[콜라,1500,20];[사이다,1000,10]", input);
                    assertThat(output()).contains(ErrorMessage.AMOUNT_NUMBER_FORMAT_ERROR.getErrorMessage());
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12", "1234"})
    void 투입금액_10원단위_아니면_예외_발생(String input) {
        assertSimpleTest(
                () -> {
                    runException("450", "[콜라,1500,20];[사이다,1000,10]", input);
                    assertThat(output()).contains(ErrorMessage.AMOUNT_UNIT_ERROR.getErrorMessage());
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"환타"})
    void 구매_상품_존재하지_않으면_예외_발생(String input) {
        assertSimpleTest(
                () -> {
                    runException("450", "[콜라,1500,20];[사이다,1000,10]", "3000", input);
                    assertThat(output()).contains(ErrorMessage.NO_EXIST_ITEM_ERROR.getErrorMessage());
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"콜라"})
    void 구매_상품_재고가_0개이면_예외_발생(String input) {
        assertSimpleTest(
                () -> {
                    runException("450", "[콜라,1500,0];[사이다,1000,10]", "3000", input);
                    assertThat(output()).contains(ErrorMessage.EMPTY_STOCK_ITEM_ERROR.getErrorMessage());
                }
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
