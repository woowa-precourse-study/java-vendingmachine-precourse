package vendingmachine.controller;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Products;
import vendingmachine.service.VendingService;

import java.util.List;
import java.util.Map;

public class VendingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final VendingService vendingService;

    public VendingController(VendingService vendingService) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.vendingService = vendingService;
    }

    public void run() {
        int vendingMoney = inputView.readMachineMoney();
        Map<Coin, Integer> coinCount = vendingService.getRandomCoinCount(vendingMoney);
        outputView.printVendingMoneyCount(coinCount);

        List<String> productInfos = inputView.readProductInfo();
        Products products = new Products(productInfos);

        int remain = inputView.readInputMoney();
        if (!products.isPurchaseAvailable(remain)) {
            System.out.printf("\n투입 금액: %s원\n", remain);
        }
        remain = repeatPurchase(remain, products);
        outputView.printRemain(remain);
        outputView.printResult(remain, coinCount);

    }

    private int repeatPurchase(int remain, Products products) {
        while (products.isPurchaseAvailable(remain)) {
            outputView.printRemain(remain);
            String purchaseProduct = inputView.readPurchaseProduct();
            try{
                remain -= products.purchase(purchaseProduct);
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                continue;
            }
        }
        return remain;
    }
}
