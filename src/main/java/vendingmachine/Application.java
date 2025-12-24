package vendingmachine;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.exception.Validator;

import java.util.*;

public class Application {

    // ===== 상수 =====

    static String PREFIX_ERROR="[ERROR] ";
    static final int MAX_RETRY = 10;


    // ===== main / run =====

    public static void main(String[] args) {
        run();
    }


    static void run() {
        try{
            System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
            // 숫자 입력
            String number = readInputWithRetry(List.of(
                    Validator::validateNotBlank,
                    Validator::validateIsNumber,
                    Validator::validatePositiveNumber
            ));

            int price = Integer.parseInt(number);

            // 자판기 보유한 동전 출력
            System.out.println("자판기가 보유한 동전");


//            // 두번째 입력
//            String input = readInputWithRetry(List.of(
//                    Validator::validateNotBlank,
//                    Validator::validateNotBlank
//            ));

        } catch(IllegalArgumentException | NoSuchElementException e){ // 입력안함은 여기서 자동 제거
            System.out.println(PREFIX_ERROR+e.getMessage());
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
     *
     *             String number = readInputWithRetry(List.of(
     *                     Validator::validateNotBlank,
     *                     Validator::validateNotNumber,
     *                     input -> Validator.validateRange(input, 1, 4),
     *                     input -> Validator.validateMaxLength(input, 4)
     *
     *             ));
     * **/

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

