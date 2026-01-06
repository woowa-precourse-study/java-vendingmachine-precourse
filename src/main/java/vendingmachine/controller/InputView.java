package vendingmachine.controller;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.exception.Validator;
import vendingmachine.utils.Parser;

import java.util.List;
import java.util.NoSuchElementException;

public class InputView {

    public int readMachineMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String input = readInput(List.of(
                Validator::validateNotBlank,
                Validator::validatePositiveNumber,
                Validator::validateCoinUnit
        ));
        return Integer.parseInt(input);
    }

    public List<String> readProducts() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        String input = readInput(List.of(
                Validator::validateNotBlank
        ));
        return Parser.splitBy(input,";");
    }

    public int readMoney() {
        System.out.println("투입 금액을 입력해 주세요.");
        String input = readInput(List.of(
                Validator::validateNotBlank,
                Validator::validatePositiveNumber
        ));
        return Integer.parseInt(input);
    }

    private String readInput(List<Validator> validators) {
        try{
            String input = Console.readLine().trim();
            for (Validator v : validators) {
                v.validate(input);
            }
            return input;
        } catch(NoSuchElementException e){
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }

    }
}