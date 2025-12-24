package vendingmachine.service;

import vendingmachine.domain.Coin;
import vendingmachine.utils.RandomGenerator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VendingService {

    public Map<Coin, Integer> getRandomCoinCount(int vendingMoney) {
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
        return coinCount;
    }

}
