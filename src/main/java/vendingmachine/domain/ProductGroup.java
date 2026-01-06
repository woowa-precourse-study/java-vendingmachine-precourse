package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductGroup {
    private final List<Product> products=new ArrayList<>();
    private int minimumPrice=0;

    public void add(Product product) {
        products.add(product);
    }

    public int decrease(String name){
        for (Product product:products){
            if (product.getName().equals(name)){
                return product.decreaseInventory();
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다.");
    }

    public boolean isInventoryEnough(int amount){
        int inventory = products.stream()
                .mapToInt(Product::getInventory)
                .sum();
        return !(inventory<amount);
    }

    public boolean isEnoughMoney(int price){
        int minPrice= products.stream()
                .mapToInt(Product::getPrice)
                .min()
                .orElse(0);
        return !(price<minPrice);
    }

}
