package vendingmachine.controller;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Products;
import vendingmachine.utils.Constants;
import vendingmachine.utils.RandomGenerator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VendingController {
    private final InputView inputView;
    private final OutputView outputView;

    public VendingController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run(){
        int vendingMoney=inputView.readMachineMoney();

        // 해당 금액이 나올 수 있는 숫자 리스트를 만들기
        Map<Coin, Integer> coinCount = new LinkedHashMap<>();
        List<Coin> coins = Coin.getCoins();
        for (Coin coin : coins) {
            coinCount.put(coin, 0);
        }

        while (vendingMoney != 0) {
            int amount = RandomGenerator.getRandomNumber(Coin.getAllCoinsAmount());
            Coin coin = Coin.getCoinByAmount(amount);
            if (amount > vendingMoney) {
                continue;
            }
            coinCount.replace(coin, coinCount.get(coin), coinCount.get(coin) + 1);
            vendingMoney -= amount;
        }
                    //Test 위한 임의 값 (랜덤 대신 지정)
//        coinCount.put(Coin.COIN_500,0);
//        coinCount.put(Coin.COIN_100,4);
//        coinCount.put(Coin.COIN_50,1);
//        coinCount.put(Coin.COIN_10,0);

        outputView.printVendingMoneyCount(coinCount);
        List<String> productInfos=inputView.readProductInfo();
        Products products = new Products(productInfos);

        int remain=inputView.readInputMoney();
        if (!products.validatePurchaseAvailable(remain)) {
            System.out.printf("\n투입 금액: %s원\n", remain);
        }

        while (products.validatePurchaseAvailable(remain)) {
            outputView.printRemain(remain);
            String purchaseProduct=inputView.readPurchaseProduct();
            products.validateProduct(purchaseProduct);
            int purchasePrice = products.getPurchaseProductAmount(purchaseProduct);
            if (purchasePrice != Constants.INVALID_PURCHASE) {
                remain -= purchasePrice;
            }

        }
        outputView.printRemain(remain);
        outputView.printResult(remain,coinCount);

    }
}
