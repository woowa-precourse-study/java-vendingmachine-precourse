package vendingmachine.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import vendingmachine.constant.Coin;
import vendingmachine.domain.VendingMachine;

public class VendingMachineService {

    private VendingMachine vendingMachine;

    public EnumMap<Coin, Integer> setVendingMachine(int money) {
        vendingMachine = VendingMachine.fromMoney(money);
        return vendingMachine.getCoinCount();
    }

    public void addItemsInVendingMachine(Map<String, List<Integer>> machineItems) {
        for (String name : machineItems.keySet()) {
            int price = machineItems.get(name).get(0);
            int count = machineItems.get(name).get(1);
            vendingMachine.addItems(name, price, count);
        }
    }

    public void insertMoney(int amount) {
        vendingMachine.insertMoney(amount);
    }

    public boolean isPossiblePurchase() {
        return vendingMachine.isPossiblePurchase();
    }

    public int getCurrentAmount() {
        return vendingMachine.getInsertedMoney().getAmount();
    }

    public void purchaseItems(String purchaseItem) {
        int purchasePrice = vendingMachine.purchaseItem(purchaseItem);
        vendingMachine.updateCurrentAmount(purchasePrice);
    }

    public EnumMap<Coin, Integer> getOptimalCoinCount() {
        return vendingMachine.getOptimalCoinCount();
    }
}
