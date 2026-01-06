package vendingmachine.domain;

import java.util.*;

public class Changes {
    private final EnumMap<Coin,Integer> changes=new EnumMap<>(Coin.class);

    public Changes() {
        for (Coin coin:Coin.values()){
            changes.put(coin,0);
        }
    }

    public List<Integer> getCoins(){
        List<Integer> coins=new ArrayList<>();
        for (Coin coin:changes.keySet()){
            coins.add(coin.getAmount());
        }
        return coins;
    }

    public void add(int amount){
        for (Coin coin:changes.keySet()){
            if (coin.getAmount()==amount){
                changes.put(coin,changes.get(coin)+1);
                return;
            };
        }
        throw new IllegalArgumentException("[ERROR] 해당 금액의 동전이 없습니다.");

    }

    public Map<Integer,Integer> getAllCoin(){
        Map<Integer,Integer> coins=new LinkedHashMap<>();
        for (Coin coin:changes.keySet()){
            coins.put(coin.getAmount(),changes.get(coin));
        }
        return coins;
    }

    public Map<Integer,Integer> getChanges(){
        Map<Integer,Integer> coins=new LinkedHashMap<>();

        for (Coin coin:changes.keySet()){
            int count=changes.get(coin);
            if (count!=0){
                coins.put(coin.getAmount(),count);
            }
        }
        return coins;
    }

    public int getTotal(){
        int total=0;
        for (Coin coin:changes.keySet()){
            total = coin.getAmount()*changes.get(coin);
        }
        return total;
    }

    private Map<Integer,Integer> calculateChanges(int remain){
        Map<Integer,Integer> finalChange=new LinkedHashMap<>();
        for (Coin coin:changes.keySet()){
            if (remain!=0 || getTotal()>=remain){
                break;
            }
            int count = coin.calculate(remain);
            if (count!=0){
                remain -=coin.getAmount()*count;
                finalChange.put(coin.getAmount(),count);
            }
        }
        return finalChange;
    }


}
