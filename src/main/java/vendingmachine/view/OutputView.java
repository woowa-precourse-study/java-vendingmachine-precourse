package vendingmachine.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vendingmachine.Coin;
import vendingmachine.controller.Result;
import vendingmachine.domain.Coins;

public class OutputView {
    private static final String PRINT_COINS = "자판기가 보유한 동전";

    public static void printPrompt(String message) {
        System.out.println(message);
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printCoins(Coins coins) {
        System.out.println(PRINT_COINS);
        Map<Coin, Integer> coinStatistic = coins.getCoins();

        List<Coin> sortedCoin = Arrays.stream(Coin.values())
                .sorted((c1, c2) -> c2.getAmount() - c1.getAmount())
                .collect(Collectors.toList());

        for (Coin coin : sortedCoin) {
            System.out.println(coin.getAmount() + "원 - " + coinStatistic.getOrDefault(coin, 0) + "개");
        }
    }

    public static void printFee(int fee) {
        System.out.printf("투입 금액: %d원\n", fee);
    }

    public static void printResult(Result result) {
        Coins coins = result.getCoins();
        int fee = result.getFee();

        System.out.println("잔돈");
        Map<Coin, Integer> restCoins = coins.calculateRestCoins(fee);

        printSortedCoin(restCoins);
    }

    private static void printSortedCoin(Map<Coin, Integer> coinStatistic) {
        List<Coin> sortedCoin = coinStatistic.keySet()
                .stream()
                .sorted((c1, c2) -> c2.getAmount() - c1.getAmount())
                .collect(Collectors.toList());

        for (Coin coin : sortedCoin) {
            System.out.println(coin.getAmount() + "원 - " + coinStatistic.getOrDefault(coin, 0) + "개");
        }
    }
}
