package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.transform.Result;
import vendingmachine.util.Validator;

public class InputView {
    public static String readInput(){
        System.out.println("입력받는 메서드");
        String input = Console.readLine();
        return input;
    }

    // 오류 발생 지점 부터 재 입력 받는 로직
    public static int readChangeAmount(){
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        try{
            String input = Console.readLine();
            Integer result = Integer.parseInt(input);
            Validator.checkType(result);
            return result;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static int readCurrentAmount(){
        System.out.println("투입 금액을 입력해 주세요.");
        try{
            String input = Console.readLine();
            return Integer.parseInt(input);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static List<String> readStockList(){
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        try{
            String input = Console.readLine();
            List<String> list = Arrays.stream(input.split(";"))
                    .collect(Collectors.toList());

            return list;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static String readTargetName(){
        System.out.println("구매할 상품명을 입력해 주세요.");
        try{
            String input = Console.readLine();
            return input;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}