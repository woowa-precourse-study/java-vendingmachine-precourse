package vendingmachine.service;

import vendingmachine.domain.*;
import vendingmachine.exception.Validator;
import vendingmachine.utils.Parser;
import vendingmachine.utils.RandomGenerator;

import java.util.List;

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

    public ProductGroup getProductGroup(List<String> inputs) {
        ProductGroup productGroup = new ProductGroup();
        for (String input: inputs){
            input=input.substring(1,input.length()-1);
            List<String> product= Parser.splitBy(input,",");

            if (product.size()!=3){
                throw new IllegalArgumentException("[ERROR] 입력 형식이 올바르지 않습니다.");
            }
            int price = Validator.validateIsNumber(product.get(1));
            int amount = Validator.validateIsNumber(product.get(2));
            productGroup.add(new Product(product.get(0),price,amount));
        }
        return productGroup;
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
