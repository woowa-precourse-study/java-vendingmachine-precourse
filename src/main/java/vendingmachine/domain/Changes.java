package vendingmachine.domain;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Changes {
    private final EnumMap<Coin,Integer> changes=new EnumMap<>(Coin.class);

    public Changes() {
        for (Coin coin:Coin.values()){
            changes.put(coin,0);
        }
    }

    public Map<Integer,Integer> getAllCoin(){
        Map<Integer,Integer> coins=new LinkedHashMap<>();
        for (Coin coin:changes.keySet()){
            coins.put(coin.getAmount(),changes.get(coin));
        }
        return coins;
    }


}
