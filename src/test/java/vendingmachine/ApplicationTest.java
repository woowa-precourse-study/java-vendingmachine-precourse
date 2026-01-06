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

//    @ParameterizedTest
//    @ValueSource(strings = {"90", "1501"})
//    void 상품가격_예외_테스트(String text) {
//        assertSimpleTest(
//                () -> {
//                    runException("1500",String.format("[콜라,%s,20];[사이다,1000,10]",text));
//                    assertThat(output()).contains("[ERROR] 최소 상품 가격은 100원이며, 1원 단위는 불가능합니다.");
//                }
//        );
//    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
