package vendingmachine.service;

import vendingmachine.domain.Coin;
import vendingmachine.utils.RandomGenerator;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class VendingService {

    public Map<Coin, Integer> getRandomCoinCount(int vendingMoney) {
        Map<Coin, Integer> coinCount = new EnumMap<>(Coin.class);;
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
            coinCount.put(coin, coinCount.get(coin) + 1);

            vendingMoney -= amount;
        }
        //Test 위한 임의 값 (랜덤 대신 지정)
//        coinCount.put(Coin.COIN_500,0);
//        coinCount.put(Coin.COIN_100,4);
//        coinCount.put(Coin.COIN_50,1);
//        coinCount.put(Coin.COIN_10,0);
        return coinCount;
    }

}
