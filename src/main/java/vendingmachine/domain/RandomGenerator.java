package vendingmachine.domain;

import java.util.List;

public interface RandomGenerator {
    int generate(List<Integer> numbers);
}
