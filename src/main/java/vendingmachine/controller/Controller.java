package vendingmachine.controller;

import vendingmachine.domain.Changes;
import vendingmachine.domain.Machine;
import vendingmachine.domain.Product;
import vendingmachine.domain.ProductGroup;
import vendingmachine.exception.Validator;
import vendingmachine.service.Service;
import vendingmachine.utils.Parser;

import java.util.List;
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
                inputView::readMachineMoney
        );

        Changes changes = service.makeChange(machineMoney);
        OutputView.printCoins(changes.getAllCoin());

        ProductGroup productGroup = doRetry(() -> {
            List<String> inputs = inputView.readProducts();
            return service.getProductGroup(inputs);
        });

        int payMoney = doRetry(inputView::readMoney);
        Machine machine = new Machine(changes,productGroup);
        machine.addMoney(payMoney);



        while(true){
            if (!machine.isAvailablePurchase()){
                int remain=machine.getRemain();
                OutputView.printRemain(remain);
                OutputView.printChanges(machine.getFinalChange());

                break;
            }

            doRetry(() -> {
                OutputView.printRemain(machine.getRemain());
                String product = inputView.readPurchase();
                machine.purchase(product);
                return product;
            });
        }
    }

    private <T> T doRetry(Supplier<T> action) {
        int retry = 0;
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                retry++;
                System.out.println(e.getMessage());

                if (retry >= MAX_RETRY) {
                    throw new IllegalStateException("입력 횟수를 초과했습니다.");
                }
            }
        }
    }


}

