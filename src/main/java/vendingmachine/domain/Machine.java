package vendingmachine.domain;

public class Machine {
    private final Changes changes;
    private final ProductGroup products;
    private int remain=0;

    public Machine(Changes changes, ProductGroup products) {
        this.changes = changes;
        this.products = products;
    }

    public void addMoney(int money){
        remain+=money;
    }

    public boolean isAvailablePurchase(){
        return products.isInventoryEnough(0) && products.isEnoughMoney(remain);
    }

    public int getRemain() {
        return remain;
    }

    public void purchase(String name){
        int price=products.decrease(name);
        remain-=price;
    }


}
