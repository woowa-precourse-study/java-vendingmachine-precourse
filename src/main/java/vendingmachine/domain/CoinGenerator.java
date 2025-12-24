package vendingmachine.domain;

import java.util.EnumMap;
import java.util.Map;
import vendingmachine.Coin;

public abstract class CoinGenerator {
    
    public Coins generate(int price) {
        Map<Coin, Integer> coins = new EnumMap<>(Coin.class);

        while (Coin.canGenerate(price)) {
            int random = generateNumber(price);
            Coin coin = Coin.valueOf(random);

            coins.put(coin, coins.getOrDefault(coin, 0) + 1);
            price -= coin.getAmount();
        }

        return Coins.from(coins);
    }

    public abstract int generateNumber(int number);
}
