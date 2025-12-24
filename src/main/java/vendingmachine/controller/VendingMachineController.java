package vendingmachine.controller;

import static vendingmachine.controller.PrintMessage.VENDING_MACHINE_CHANGE;

import java.util.List;
import java.util.function.Supplier;
import vendingmachine.domain.VendingMachine;
import vendingmachine.domain.coingenerator.RandomCoinGenerator;
import vendingmachine.factory.ProductFactory;
import vendingmachine.util.InputParser;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
    public void run() {
        int change = readChange();
        VendingMachine vendingMachine = VendingMachine.from(change, new RandomCoinGenerator());
        OutputView.printCoins(vendingMachine.getCoins());

        List<String> item = readProducts();
        VendingMachine itemVendingMachine = vendingMachine.addProducts(ProductFactory.createProducts(item));

        int fee = readUserFee();
        Result result = buyProduct(itemVendingMachine, fee);

        OutputView.printResult(result);
    }

    private int readChange() {
        return retryOnError(() -> {
            OutputView.printPrompt(VENDING_MACHINE_CHANGE.getMessage());
            String change = InputView.readChange();

            return InputParser.parseNumber(change);
        });
    }

    private List<String> readProducts() {
        return retryOnError(() -> {
            OutputView.printPrompt(PrintMessage.INPUT_PRODUCT.getMessage());
            String product = InputView.readProduct();

            return InputParser.parseProduct(product);
        });
    }

    private static int readUserFee() {
        OutputView.printPrompt(PrintMessage.INPUT_USER_FEE.getMessage());
        String fee = InputView.readUserFee();

        return InputParser.parseNumber(fee);
    }

    private Result buyProduct(VendingMachine vendingMachine, int fee) {
        VendingMachine newVendingMachine = vendingMachine;

        while (true) {
            OutputView.printFee(fee);

            if (!newVendingMachine.canBuy(fee)) {
                return new Result(newVendingMachine.getCoins(), fee);
            }

            String product = retryOnError(() -> {
                OutputView.printPrompt(PrintMessage.INPUT_BUY_PRODUCT.getMessage());
                String productName = InputView.readProductName();

                vendingMachine.hasProduct(productName);

                return productName;
            });

            fee = vendingMachine.buyProduct(fee, product);
            newVendingMachine = newVendingMachine.buyProduct(product);
        }
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
