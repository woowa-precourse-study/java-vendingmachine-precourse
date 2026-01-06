package vendingmachine.controller;


import java.util.Map;

public class OutputView {
    public static void printCoins(Map<Integer,Integer> coins){
        System.out.println("자판기가 보유한 동전");
        for (Integer coin : coins.keySet()){
            System.out.printf("%s원 - %d개\n",coin,coins.get(coin));
        }
        System.out.println("");
    }

    public static void printRemain(int remain){
        System.out.printf("투입 금액: %s원\n",remain);
    }
}
