package vendingmachine.utils;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomGenerator {
    public static int getRandomNumber(List<Integer> numbers) {
        return Randoms.pickNumberInList(numbers);
    }

}

