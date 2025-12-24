package vendingmachine.controller;

import static vendingmachine.controller.PrintMessage.VENDING_MACHINE_CHANGE;

import java.util.function.Supplier;
import vendingmachine.domain.VendingMachine;
import vendingmachine.domain.coingenerator.RandomCoinGenerator;
import vendingmachine.util.InputParser;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
    public void run() {
        int change = readChange();
        VendingMachine vendingMachine = VendingMachine.from(change, new RandomCoinGenerator());
    }

    private int readChange() {
        OutputView.printPrompt(VENDING_MACHINE_CHANGE.getMessage());
        String change = InputView.readChange();

        return InputParser.parseNumber(change);
    }

    private <T> T retryOnError(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
