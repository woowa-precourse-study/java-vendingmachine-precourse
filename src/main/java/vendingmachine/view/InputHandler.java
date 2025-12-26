package vendingmachine.view;

import java.util.function.Supplier;

public class InputHandler {
    public static <T> T retry(Supplier<T> supplier){
        while(true){
            try{
                return supplier.get();
            }catch(IllegalStateException | IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}