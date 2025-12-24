package vendingmachine;

import vendingmachine.controller.VendingController;
import vendingmachine.service.VendingService;
import vendingmachine.utils.Constants;

public class Application {

    public static void main(String[] args) {
        VendingService vendingService = new VendingService();
        VendingController vendingController = new VendingController(vendingService);
        try {
            vendingController.run();
        } catch (IllegalStateException e) {
            System.out.println(Constants.PREFIX_ERROR + e.getMessage());
        }
    }

}

