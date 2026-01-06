package vendingmachine.service;

import vendingmachine.domain.Changes;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Machine;
import vendingmachine.utils.RandomGenerator;

public class Service {

    public Changes makeChange(int money){
        Changes changes = new Changes();
        while(money!=0){
            int randomCoin = RandomGenerator.getRandomNumber(changes.getCoins());
            if (randomCoin<=money){
                changes.add(randomCoin);
                money-=randomCoin;
            }
        }
        return changes;
    }

//    public void startMachine(){
//        Machine machine=new Machine();
//        if (machine.validateAvailablePurchase()){
//            // TODO
//            return true;
//        }
//        return false;
//    }
}
