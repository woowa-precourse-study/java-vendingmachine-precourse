package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;


public class RandomNumberGenerator implements RandomGenerator{
    @Override
    public int generate(List<Integer> numbers){
        int random = Randoms.pickNumberInList(numbers);
        return random;
    }


}
