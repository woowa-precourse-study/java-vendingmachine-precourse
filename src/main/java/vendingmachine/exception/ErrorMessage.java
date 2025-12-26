package vendingmachine.exception;

public enum ErrorMessage{
    INVALID_INPUT("입력 값이 올바르지 않습니다."),
    INVALID_TYPE("형식이 올바르지 않습니다.")
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        String prefix = "[ERROR]";
        return prefix + message;
    }
}