package vendingmachine.controller;

import vendingmachine.domain.Coin;

import java.util.Map;

public class OutputView {

    public void printVendingMoneyCount(Map<Coin, Integer> coinCount) {
        System.out.println("자판기가 보유한 동전");
        coinCount.forEach((key, value) -> {
            System.out.printf("%d원 - %d개\n", key.getAmount(), value);
        });
    }

    public void printRemain(int remain) {
        System.out.printf("\n투입 금액: %s원\n", remain);
    }

    public void printResult(int remain, Map<Coin, Integer> coinCount) {
        System.out.println("잔돈");
        Map<String, Integer> changes = Coin.getChange(remain, coinCount);
        for (String coin : changes.keySet()) {
            if (changes.get(coin) != 0) {
                System.out.printf("%s원 - %d개\n", coin, changes.get(coin));
            }
        }
    }
}
