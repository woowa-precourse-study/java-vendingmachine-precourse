package vendingmachine;

import vendingmachine.controller.VendingController;
import vendingmachine.utils.Constants;

public class Application {

    public static void main(String[] args) {
        VendingController vendingController = new VendingController();
        try {
            vendingController.run();
        } catch (IllegalStateException e) {
            System.out.println(Constants.PREFIX_ERROR+e.getMessage());
        }
    }

}

