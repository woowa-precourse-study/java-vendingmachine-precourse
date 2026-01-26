package vendingmachine.controller;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import vendingmachine.constant.Coin;
import vendingmachine.service.VendingMachineService;
import vendingmachine.util.InputParser;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

    private final VendingMachineService vendingMachineService;

    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    public void run() {
        EnumMap<Coin, Integer> coinCount = setVendingMachine();

        OutputView.printMachineCoins(coinCount);

        addItemsInVendingMachine();

        insertMoney();

        while (vendingMachineService.isPossiblePurchase()) {
            OutputView.printCurrentAmount(vendingMachineService.getCurrentAmount());
            purchaseItems();
        }

        OutputView.printCurrentAmount(vendingMachineService.getCurrentAmount());
        OutputView.printChangeCoins(vendingMachineService.getOptimalCoinCount());
    }

    private EnumMap<Coin, Integer> setVendingMachine() {
        return retryUntilSuccess(() -> {
            String readMachineMoney = InputView.readMachineMoney();
            int money = InputParser.parseMachineMoney(readMachineMoney);
            return vendingMachineService.setVendingMachine(money);
        });
    }

    private void addItemsInVendingMachine() {
        retryUntilSuccess(() -> {
            String readItems = InputView.readMachineItems();
            Map<String, List<Integer>> machineItems = InputParser.parseMachineItems(readItems);
            vendingMachineService.addItemsInVendingMachine(machineItems);
        });
    }

    private void purchaseItems() {
        retryUntilSuccess(() -> {
            String readPurChaseItem = InputView.readPurchaseItem();
            String purchaseItem = InputParser.parsePurchaseItem(readPurChaseItem);
            vendingMachineService.purchaseItems(purchaseItem);
        });
    }

    private void insertMoney() {
        retryUntilSuccess(() -> {
            String readInsertedMoney = InputView.readInsertedMoney();
            int amount = InputParser.parseInsertedMoney(readInsertedMoney);

            vendingMachineService.insertMoney(amount);
        });
    }

    private <T> T retryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private void retryUntilSuccess(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }
}
