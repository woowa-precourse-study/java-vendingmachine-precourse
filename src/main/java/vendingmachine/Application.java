package vendingmachine;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.exception.Validator;
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
                    Validator::validateIsNumber,
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


//            // 두번째 입력
//            String input = readInputWithRetry(List.of(
//                    Validator::validateNotBlank,
//                    Validator::validateNotBlank
//            ));

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

