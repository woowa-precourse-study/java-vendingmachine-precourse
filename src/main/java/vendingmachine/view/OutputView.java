package vendingmachine.view;

import java.util.EnumMap;
import vendingmachine.constant.Coin;

public class OutputView {

    private static final String COINS = "\n자판기가 보유한 동전";
    private static final String COINS_RESULT = "%d원 - %d개\n";
    private static final String AMOUNT_RESULT = "\n투입 금액: %d원\n";
    private static final String CHANGE = "잔돈";

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void printMachineCoins(EnumMap<Coin, Integer> coinCount) {
        System.out.println(COINS);
        for (Coin coin : coinCount.keySet()) {
            System.out.printf(COINS_RESULT, coin.getAmount(), coinCount.get(coin));
        }
    }

    public static void printCurrentAmount(int amount) {
        System.out.printf(AMOUNT_RESULT, amount);
    }

    public static void printChangeCoins(EnumMap<Coin, Integer> optimalCoinCount) {
        System.out.println(CHANGE);
        for (Coin coin : optimalCoinCount.keySet()) {
            System.out.printf(COINS_RESULT, coin.getAmount(), optimalCoinCount.get(coin));
        }
    }
}
