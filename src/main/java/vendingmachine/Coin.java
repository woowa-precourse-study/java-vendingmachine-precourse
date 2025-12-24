package vendingmachine;

import java.util.Arrays;

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
    public static Coin valueOf(int value) {

        return Arrays.stream(Coin.values())
                .sorted((c1, c2) -> c2.amount - c1.amount)
                .filter(coin -> value >= coin.amount)
                .findAny().orElse(null);
    }

    public static boolean canGenerate(int price) {
        return price >= Coin.COIN_10.amount;
    }

    public int getAmount() {
        return amount;
    }
}
