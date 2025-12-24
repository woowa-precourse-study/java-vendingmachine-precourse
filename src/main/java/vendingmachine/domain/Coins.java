package vendingmachine.domain;

import java.util.Map;
import vendingmachine.Coin;

public class Coins {
    private final Map<Coin, Integer> coins;

    private Coins(Map<Coin, Integer> coins) {
        this.coins = coins;
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }

    public static Coins from(Map<Coin, Integer> coins) {
        return new Coins(coins);
    }
}
