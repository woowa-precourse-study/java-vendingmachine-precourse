package vendingmachine.util;

import vendingmachine.exception.ErrorMessage;

public class Validator{

    // 빈 값 에러
    public static void checkNull(String input){
        if(input.isEmpty()){
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
    }
    public static void checkType(int input){
        if(input < 100 || input % 10 !=0){
            throw new IllegalArgumentException(ErrorMessage.INVALID_TYPE.getMessage());
        }
    }
    // 길이 미충족 에러
    public static void checkLength(){
        //TODO
    }

    // 중복 값 에러
    public static void checkDuplicate(){
        //TODO
    }
}