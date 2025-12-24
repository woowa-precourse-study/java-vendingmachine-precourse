package vendingmachine.factory;

import vendingmachine.controller.VendingMachineController;

public class ApplicationFactory {

    public VendingMachineController controller() {
        return new VendingMachineController();
    }
}
