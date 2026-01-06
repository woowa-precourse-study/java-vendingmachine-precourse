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

    public boolean validateAvailablePurchase(){
        return products.isInventoryEnough(0) && products.isEnoughMoney(remain);
    }

    public void purchase(String name){
        // TODO: 하나의 상품 구매
    }


}
