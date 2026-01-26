package vendingmachine.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.constant.Coin;
import vendingmachine.constant.ErrorMessage;
import vendingmachine.generator.RandomCoinGenerator;

public class VendingMachine {

    private static final int UNIT = 10;

    private final EnumMap<Coin, Integer> coinCount;
    private final Map<Item, Integer> items;
    private InsertedMoney insertedMoney;

    public VendingMachine(EnumMap<Coin, Integer> coinCount) {
        this.coinCount = coinCount;
        this.items = new HashMap<>();
    }

    public static VendingMachine fromMoney(int money) {
        validate(money);

        EnumMap<Coin, Integer> count = new EnumMap<>(Coin.class);

        Arrays.stream(Coin.values())
                .forEach(coin -> count.put(coin, 0));

        while (money > 0) {
            int generatedAmount = RandomCoinGenerator.generateCoin();
            if (generatedAmount <= money) {
                count.put(Coin.fromAmount(generatedAmount), count.get(Coin.fromAmount(generatedAmount)) + 1);
                money -= generatedAmount;
            }
        }

        return new VendingMachine(count);
    }

    private static void validate(int money) {
        if (money % UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_UNIT_ERROR.getErrorMessage());
        }
    }

    public void addItems(String name, int price, int count) {
        Item item = new Item(name, price);
        items.put(item, count);
    }

    public boolean isPossiblePurchase() {
        int minPrice = getMinPriceOfItem();
        return insertedMoney.getAmount() >= minPrice && insertedMoney.getAmount() != 0 && isNotSoldOut();
    }

    private int getMinPriceOfItem() {
        List<Item> items = new ArrayList<>(this.items.keySet());

        return items.stream()
                .map(Item::getPrice)
                .min(Integer::compare)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NO_MIN_PRICE_ERROR.getErrorMessage()));
    }

    private boolean isNotSoldOut() {
        return new ArrayList<>(items.values())
                .stream().anyMatch(count -> count > 0);
    }

    public int purchaseItem(String purchaseItem) {
        for (Item item : items.keySet()) {
            if (item.getName().equals(purchaseItem)) {
                if (items.get(item) == 0) {
                    throw new IllegalArgumentException(ErrorMessage.EMPTY_STOCK_ITEM_ERROR.getErrorMessage());
                }

                items.put(item, items.get(item) - 1);

                return item.getPrice();
            }
        }
        throw new IllegalArgumentException(ErrorMessage.NO_EXIST_ITEM_ERROR.getErrorMessage());
    }

    public EnumMap<Coin, Integer> getOptimalCoinCount() {
        EnumMap<Coin, Integer> optimalCoinCount = new EnumMap<>(Coin.class);

        int amount = insertedMoney.getAmount();
        for (Coin coin : Coin.values()) {
            int count = amount / coin.getAmount();
            if (count > 0 && coinCount.get(coin) != 0) {
                count = computeCount(coin, count);
                optimalCoinCount.put(coin, count);
                amount -= coin.getAmount() * count;
            }

            if (amount < UNIT) {
                break;
            }
        }

        return optimalCoinCount;
    }

    private int computeCount(Coin coin, int count) {
        if (count >= coinCount.get(coin)) {
            count = coinCount.get(coin);
        }
        return count;
    }

    public void insertMoney(int amount) {
        insertedMoney = InsertedMoney.fromAmount(amount);
    }

    public EnumMap<Coin, Integer> getCoinCount() {
        return new EnumMap<>(coinCount);
    }

    public InsertedMoney getInsertedMoney() {
        return insertedMoney;
    }

    public void updateCurrentAmount(int purchasePrice) {
        insertedMoney.updateAmount(purchasePrice);
    }
}
