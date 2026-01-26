package vendingmachine.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.constant.ErrorMessage;

public final class InputParser {

    private static final String FIRST_DELIMITER = ";";
    private static final String SECOND_DELIMITER = ",";

    private InputParser() {
    }

    public static Integer parseMachineMoney(String rawMoney) {
        rawMoney = rawMoney.trim();

        Validator.validateAmountNumberFormat(rawMoney);

        return NumberConvertor.convertToNumber(rawMoney);
    }

    public static Map<String, List<Integer>> parseMachineItems(String readItems) {
        Validator.validateMachineItemsFormat(readItems);
        readItems = readItems.trim();

        Map<String, List<Integer>> machineItems = new HashMap<>();

        String[] split = readItems.split(FIRST_DELIMITER);
        for (String s : split) {
            String[] order = s.replace("[","").replace("]", "")
                    .trim().split(SECOND_DELIMITER);
            parseDetails(order, machineItems);
        }

        return machineItems;
    }

    private static void parseDetails(String[] order, Map<String, List<Integer>> machineItems) {
        String name = order[0];
        int price = NumberConvertor.convertToNumber(order[1]);
        int count = NumberConvertor.convertToNumber(order[2]);

        if (machineItems.containsKey(name)) {
            throw new IllegalArgumentException(ErrorMessage.ITEM_NAME_UNIQUE_ERROR.getErrorMessage());
        }

        machineItems.put(name, Arrays.asList(price, count));
    }

    public static int parseInsertedMoney(String readInsertedMoney) {
        readInsertedMoney = readInsertedMoney.trim();

        Validator.validateAmountNumberFormat(readInsertedMoney);

        return NumberConvertor.convertToNumber(readInsertedMoney);
    }

    public static String parsePurchaseItem(String readPurChaseItem) {
        readPurChaseItem = readPurChaseItem.trim();

        return readPurChaseItem;
    }
}
