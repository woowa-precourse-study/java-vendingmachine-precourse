package vendingmachine;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Products;
import vendingmachine.exception.Validator;
import vendingmachine.utils.Parser;
import vendingmachine.utils.RandomGenerator;

import java.util.*;

public class Application {

    // ===== 상수 =====

    static String PREFIX_ERROR = "[ERROR] ";
    static final int MAX_RETRY = 10;


    // ===== main / run =====

    public static void main(String[] args) {
        try {
            run();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    static void run() {
        try {
            System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
            // 숫자 입력
            String number = readInputWithRetry(List.of(
                    Validator::validateNotBlank,
                    Validator::validatePositiveNumber
            ));

            int price = Integer.parseInt(number);

            // 해당 금액이 나올 수 있는 숫자 리스트를 만들기
            Map<Coin, Integer> coinCount = new LinkedHashMap<>();
            List<Coin> coins = Coin.getCoins();
            for (Coin coin : coins) {
                coinCount.put(coin, 0);
            }

            while (price != 0) {
                int amount = RandomGenerator.getRandomNumber(Coin.getAllCoinsAmount());
                Coin coin = Coin.getCoinByAmount(amount);
                if (amount > price) {
                    continue;
                }
                coinCount.replace(coin, coinCount.get(coin), coinCount.get(coin) + 1);
                price -= amount;
            }

            // 자판기 보유한 동전 출력
            System.out.println("자판기가 보유한 동전");
            coinCount.forEach((key, value) -> {
                System.out.printf("%d원 - %d개\n", key.getAmount(), value);
            });


            // 두번째 입력
            System.out.println("상품명과 가격, 수량을 입력해 주세요.");
            String input = readInputWithRetry(List.of(
                    Validator::validateNotBlank,
                    Validator::validatePurchaseInputFormat
            ));

            //Parser
            List<String> inputs = Parser.splitStringToListWithSymbols(input, ";");

            Products products = new Products(inputs);


            // 세번째 입력
            System.out.println("투입 금액을 입력해 주세요.");
            String num = readInputWithRetry(List.of(
                    Validator::validateNotBlank,
                    Validator::validatePositiveNumber
            ));


            // 네번째 입력 (n번 반복)
            System.out.printf("\n투입 금액: %s원\n", num);
            int inputPrice = Integer.parseInt(num);
            // TODO: 남은 금액이 상품의 최저 가격보다 적거나, 모든 상품이 소진된 경우 바로 잔돈을 돌려준다.

            System.out.println("구매할 상품명을 입력해 주세요.");
            String inputProduct = readInputWithRetry(List.of(
                    Validator::validateNotBlank
            ));
            // TODO: 구매할 상품이 존재하는지 확인 필


        } catch (IllegalArgumentException | NoSuchElementException e) { // 입력안함은 여기서 자동 제거
            System.out.println(PREFIX_ERROR + e.getMessage());
        }

    }


    static void attendance() {
        System.out.println("출석 처리");
    }

    static void modify() {
        System.out.println("출석 수정");
    }

    /**
     * 입력 관련 메서드
     * 아래와 같이 검증들을 input 파라미터로 넣어준다.
     * <p>
     * String number = readInputWithRetry(List.of(
     * Validator::validateNotBlank,
     * Validator::validateNotNumber,
     * input -> Validator.validateRange(input, 1, 4),
     * input -> Validator.validateMaxLength(input, 4)
     * <p>
     * ));
     **/

    static String readInput(List<Validator> validators) {
        String input = Console.readLine();
        for (Validator v : validators) {
            v.validate(input);
        }
        return input;
    }


    static String readInputWithRetry(List<Validator> validators) {
        int retry = 0;
        while (true) {
            try {
                return readInput(validators);
            } catch (IllegalArgumentException | NoSuchElementException e) {
                retry++;
                System.out.println(PREFIX_ERROR + e.getMessage());

                if (retry >= MAX_RETRY) {
                    throw new IllegalStateException("입력 횟수를 초과했습니다.");
                }
            }
        }
    }

}

