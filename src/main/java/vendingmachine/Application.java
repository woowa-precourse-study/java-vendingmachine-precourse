package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.factory.ApplicationFactory;

public class Application {
    public static void main(String[] args) {
        ApplicationFactory factory = new ApplicationFactory();
        VendingMachineController controller = factory.controller();

        controller.run();
    }
}
