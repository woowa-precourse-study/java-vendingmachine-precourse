package vendingmachine.controller;

import vendingmachine.domain.Coins;

public class Result {
    private final Coins coins;
    private final int fee;

    public Result(Coins coins, int fee) {
        this.coins = coins;
        this.fee = fee;
    }

    public Coins getCoins() {
        return coins;
    }

    public int getFee() {
        return fee;
    }
}
