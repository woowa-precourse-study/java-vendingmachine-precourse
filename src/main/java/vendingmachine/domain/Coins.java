package vendingmachine.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vendingmachine.Coin;

public class Coins {
    private final Map<Coin, Integer> coins;

    private Coins(Map<Coin, Integer> coins) {
        this.coins = coins;
    }

    public Map<Coin, Integer> getCoins() {
        return Map.copyOf(coins);
    }

    public Map<Coin, Integer> calculateRestCoins(int price) {
        Map<Coin, Integer> result = new EnumMap<>(Coin.class);

        List<Coin> sortedCoin = coins.keySet().stream()
                .sorted((c1, c2) -> c2.getAmount() - c1.getAmount())
                .collect(Collectors.toList());

        for (Coin coin : sortedCoin) {
            Integer value = coins.get(coin);

            if (value >= coin.getCount(price)) {
                int count = coin.getCount(price);

                price -= count * coin.getAmount();
                result.put(coin, count);
                continue;
            }

            price -= value * coin.getAmount();
            result.put(coin, value);
        }

        return result;
    }

    public static Coins from(Map<Coin, Integer> coins) {
        return new Coins(coins);
    }
}
