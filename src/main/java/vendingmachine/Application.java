package vendingmachine;

import vendingmachine.controller.Controller;
import vendingmachine.service.Service;

public class Application {

    public static void main(String[] args) {
        Service service = new Service();
        Controller controller = new Controller(service);
        try {
            controller.run();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}

