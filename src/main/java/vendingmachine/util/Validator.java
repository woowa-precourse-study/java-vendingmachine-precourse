package vendingmachine.util;

import vendingmachine.constant.ErrorMessage;

public final class Validator {

    private static final String INPUT_FORMAT = "^ *(\\[[가-힣]+,\\d+,\\d+]) *(; *(\\[[가-힣]+,\\d+,\\d+])+ *)*$";
    private static final String NUMBER_FORMAT = "\\d+";

    private Validator() {}

    public static void validateAmountNumberFormat(String input) {
        if (!input.matches(NUMBER_FORMAT)) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_NUMBER_FORMAT_ERROR.getErrorMessage());
        }
    }

    public static void validateMachineItemsFormat(String readItems) {
        if (!readItems.matches(INPUT_FORMAT)) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_FORMAT_ERROR.getErrorMessage());
        }
    }
}
