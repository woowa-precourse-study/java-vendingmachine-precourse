package vendingmachine.utils;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static List<String> splitBy(String input, String symbols) {
        return Arrays.stream(input.split(symbols))
                .map(String::trim)
                .toList();
    }
}
