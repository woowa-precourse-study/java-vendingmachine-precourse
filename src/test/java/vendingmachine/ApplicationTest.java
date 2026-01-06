package vendingmachine;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    void 예외_테스트() {
        assertSimpleTest(
                () -> {
                    runException("-1");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    /**
     * 아래는 내가 작성한 테스트
     * **/

    @Test
    void 기능_전체_테스트() {
        assertRandomNumberInListTest(
                () -> {
                    run("450", "[콜라,1500,20];[사이다,1000,10]", "3000", "콜라", "사이다");
                    assertThat(output()).contains(
                            "자판기가 보유한 동전", "500원 - 0개", "100원 - 4개", "50원 - 1개", "10원 - 0개",
                            "투입 금액: 3000원", "투입 금액: 1500원","투입 금액: 500원","100원 - 4개","50원 - 1개"
                    );
                },
                100, 100, 100, 100, 50
        );
    }

    @Test
    void 존재하지않는상품_예외_테스트() {
        assertSimpleTest(
                () -> {
                    runException("450", "[콜라,1500,20];[사이다,1000,10]", "3000","라면");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Test
    void 재고부족_예외_테스트() {
        assertSimpleTest(
                () -> {
                    runException("450", "[콜라,1500,0];[사이다,1000,10]", "3000","콜라");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }


    @Test
    void 금액부족_정상_테스트() {
        assertRandomNumberInListTest(
                () -> {
                    run("450", "[콜라,1500,0];[사이다,1000,10]", "900");
                    assertThat(output()).contains(
                            "자판기가 보유한 동전", "500원 - 0개", "100원 - 4개", "50원 - 1개", "10원 - 0개",
                            "투입 금액: 900원","100원 - 4개","50원 - 1개"
                    );
                },
                100, 100, 100, 100, 50
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"2","a"})
    void 금액예외_테스트(String text) {
        assertSimpleTest(
                () -> {
                    runException(text);
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"[콜라,1,20];[사이다,s,10]"})
    void 상품예외_테스트(String text) {
        assertSimpleTest(
                () -> {
                    runException("450",text);
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}