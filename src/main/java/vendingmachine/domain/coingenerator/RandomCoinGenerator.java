package vendingmachine.domain.coingenerator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.Coin;
import vendingmachine.domain.CoinGenerator;

public class RandomCoinGenerator extends CoinGenerator {

    @Override
    public int generateNumber(int number) {

        List<Integer> coins = Arrays.stream(Coin.values())
                .map(Coin::getAmount)
                .filter(amount -> amount <= number)
                .collect(Collectors.toList());

        return Randoms.pickNumberInList(coins);
    }
}
