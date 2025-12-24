package vendingmachine.controller;

public enum PrintMessage {
    VENDING_MACHINE_CHANGE("자판기가 보유하고 있는 금액을 입력해 주세요.");

    private final String message;

    PrintMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
