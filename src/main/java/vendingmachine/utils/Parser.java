package vendingmachine.utils;

import java.util.List;

public class Parser {
    public static List<String> splitStringToListWithSymbols(String input, String symbols) {
        return List.of(input.split(symbols));
    }

}
