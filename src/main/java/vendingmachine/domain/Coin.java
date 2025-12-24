package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    // 추가 기능 구현
    public static List<Coin> getCoins() {
        return List.of(Coin.values());
    }

    public static List<Integer> getAllCoinsAmount() {
        List<Integer> allAmount = new ArrayList<>();
        for (Coin coin : List.of(Coin.values())) {
            allAmount.add(coin.amount);
        }
        return allAmount;
    }

    public static Coin getCoinByAmount(Integer value) {
        for (Coin coin : Coin.values()) {
            if (coin.amount == value) {
                return coin;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 카테고리");
    }

    public int getAmount() {
        return amount;
    }

}
