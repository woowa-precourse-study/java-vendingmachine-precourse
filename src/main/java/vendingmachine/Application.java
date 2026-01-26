package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.service.VendingMachineService;

public class Application {

    public static void main(String[] args) {
        VendingMachineService vendingMachineService = new VendingMachineService();
        VendingMachineController vendingMachineController = new VendingMachineController(vendingMachineService);
        vendingMachineController.run();
    }
}
