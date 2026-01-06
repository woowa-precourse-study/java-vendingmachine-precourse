package vendingmachine.domain;

import java.util.Objects;

public class Product {
    private final String name;
    private final int price;
    private int inventory;

    public Product(String name, int price, int inventory) {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }

    public void isInventoryEnough(){
        if (inventory==0){
            throw new IllegalArgumentException("[ERROR] 재고가 부족합니다.");
        };
    }

    public void decreaseInventory(){
        isInventoryEnough();
        inventory--;
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
