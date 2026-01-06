package vendingmachine.domain;

import java.util.Objects;

public class Product {
    private final String name;
    private final int price;
    private int inventory;

    public Product(String name, int price, int inventory) {
        validateRange(price);
        validateCoinUnit(price);
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }

    private void validateRange(int money) {
        if (money<100) {
            throw new IllegalArgumentException("[ERROR] 상품 가격은 최소 100원이어야합니다.");
        }
    }

    private void validateCoinUnit(int money) {
        if (money % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 10원으로 나누어떨어져야합니다.");
        }
    }

    public void isInventoryEnough(){
        if (inventory==0){
            throw new IllegalArgumentException("[ERROR] 재고가 부족합니다.");
        };
    }

    public int decreaseInventory(){
        isInventoryEnough();
        inventory--;
        return price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
