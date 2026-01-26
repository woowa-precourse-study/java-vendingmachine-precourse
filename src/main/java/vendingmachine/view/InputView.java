package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String MACHINE_MONEY_REQUEST = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String MACHINE_ITEMS_REQUEST = "\n상품명과 가격, 수량을 입력해 주세요.";
    private static final String INSERTED_MONEY_REQUEST = "\n투입 금액을 입력해 주세요.";
    private static final String PURCHASE_ITEMS_REQUEST = "구매할 상품명을 입력해 주세요.";

    public static String readMachineMoney() {
        System.out.println(MACHINE_MONEY_REQUEST);
        return Console.readLine();
    }

    public static String readMachineItems() {
        System.out.println(MACHINE_ITEMS_REQUEST);
        return Console.readLine();
    }

    public static String readInsertedMoney() {
        System.out.println(INSERTED_MONEY_REQUEST);
        return Console.readLine();
    }

    public static String readPurchaseItem() {
        System.out.println(PURCHASE_ITEMS_REQUEST);
        return Console.readLine();
    }
}
