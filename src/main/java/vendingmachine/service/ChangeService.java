package vendingmachine.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vendingmachine.Coin;
import vendingmachine.domain.RandomNumberGenerator;

public class ChangeService {
    private RandomNumberGenerator generator = new RandomNumberGenerator();

    public Map<Coin,Integer> getChangeMap(int changeAmount){
        Map<Coin, Integer> changeMap = new EnumMap<>(Coin.class);
        Arrays.stream(Coin.values()).forEach(coin -> changeMap.put(coin, 0));

        while (changeAmount > 0) {
            // 1. 현재 남은 금액(changeAmount)보다 작거나 같은 동전들만 랜덤 후보로 선정
            int finalChangeAmount = changeAmount;
            List<Integer> availableAmounts = Arrays.stream(Coin.values())
                    .map(Coin::getAmount)
                    .filter(amount -> amount <= finalChangeAmount) // 남은 금액 이하만 필터링
                    .collect(Collectors.toList());

            // 2. 만약 더 이상 뽑을 수 있는 동전이 없다면 탈출 (1원 단위 등이 남았을 경우 방지)
            if (availableAmounts.isEmpty()) {
                break;
            }

            // 3. 필터링된 후보 중에서 랜덤 추출
            int random = generator.generate(availableAmounts);

            Coin coin = Arrays.stream(Coin.values())
                    .filter(c -> c.getAmount() == random)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);

            changeMap.put(coin, changeMap.get(coin) + 1);
            changeAmount -= random;
        }
        return changeMap;
//        Map<Coin, Integer> changeMap = new EnumMap<>(Coin.class);
//        Arrays.stream(Coin.values())
//                .forEach(coin -> changeMap.put(coin, 0));
//
//        System.out.println("현재 금액" + changeAmount);
//
//        while(changeAmount > 0) {
//
//            // enum 중에서 값을 찾아오는 거 500, 100, 50, 10 중 랜덤
//
//            int finalChangeAmount = changeAmount;
//            List<Integer> allAmounts = Arrays.stream(Coin.values())
//                    .map(Coin::getAmount)
//                    .filter(amount -> amount <= finalChangeAmount)
//                    .collect(Collectors.toList());
//            if(allAmounts.isEmpty())break;
//            int random = generator.generate(allAmounts);
//            if (random <= changeAmount) {
//                Coin coin = Arrays.stream(Coin.values())
//                        .filter(c -> c.getAmount() == random)
//                        .findFirst()
//                        .orElseThrow(IllegalArgumentException::new);
//
//                changeMap.put(coin, changeMap.get(coin) + 1);
//                changeAmount -= random;
//            }
//        }
//        return changeMap;
    }
}
