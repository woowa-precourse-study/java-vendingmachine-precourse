package vendingmachine.domain.coingenerator;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.CoinGenerator;

public class RandomCoinGenerator extends CoinGenerator {
    private static final int MINIMUM_VALUE = 10;

    @Override
    public int generateNumber(int number) {
        return Randoms.pickNumberInRange(MINIMUM_VALUE, number);
    }
}
