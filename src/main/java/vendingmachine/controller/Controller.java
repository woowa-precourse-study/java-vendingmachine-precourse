package vendingmachine.controller;

import vendingmachine.service.Service;

import java.util.function.Supplier;

public class Controller {
    private final InputView inputView;
    private final Service service;
    static final int MAX_RETRY = 10;

    public Controller(Service service) {
        this.inputView = new InputView();
        this.service = service;
    }

    public void run() {

        int machineMoney = doRetry(
                inputView::readMachineMoney,
                "[ERROR] 유효하지 입력입니다. 다시 입력해 주세요."
        );


    }

    private <T> T doRetry(Supplier<T> action, String errorMessage) {
        int retry = 0;
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                retry++;
                System.out.println(errorMessage);

                if (retry >= MAX_RETRY) {
                    throw new IllegalStateException("입력 횟수를 초과했습니다.");
                }
            }
        }
    }


}

