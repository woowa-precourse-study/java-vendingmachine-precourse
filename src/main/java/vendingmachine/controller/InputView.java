package vendingmachine.controller;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.exception.Validator;
import vendingmachine.utils.Parser;

import java.util.List;
import java.util.NoSuchElementException;

public class InputView {
    private static String PREFIX_ERROR = "[ERROR] ";
    private static final int MAX_RETRY = 10;

    public int readMachineMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        // 숫자 입력
        String number = readInputWithRetry(List.of(
                Validator::validateNotBlank,
                Validator::validatePositiveNumber
        ));
        return Integer.parseInt(number);
    }

    public List<String> readProductInfo() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        String input = readInputWithRetry(List.of(
                Validator::validateNotBlank,
                Validator::validatePurchaseInputFormat
        ));
        return Parser.splitBy(input, ";");
    }

    public int readInputMoney() {
        System.out.println("투입 금액을 입력해 주세요.");
        String num = readInputWithRetry(List.of(
                Validator::validateNotBlank,
                Validator::validatePositiveNumber
        ));

        return Integer.parseInt(num);
    }


    public String readPurchaseProduct() {
        System.out.println("구매할 상품명을 입력해 주세요.");
        String inputProduct = readInputWithRetry(List.of(
                Validator::validateNotBlank

        ));
        return inputProduct;
    }

    private String readInput(List<Validator> validators) {
        String input = Console.readLine();
        for (Validator v : validators) {
            v.validate(input);
        }
        return input;
    }

    private String readInputWithRetry(List<Validator> validators) {
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
