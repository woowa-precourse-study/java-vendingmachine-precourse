package vendingmachine;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import vendingmachine.domain.RandomNumberGenerator;
import vendingmachine.domain.Stock;
import vendingmachine.service.ChangeService;
import vendingmachine.view.InputHandler;
import vendingmachine.view.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ChangeService changeService = new ChangeService();
        //
//        1. 자판기 보유 금액 입력
        int changeAmount = InputHandler.retry(InputView::readChangeAmount);

//            1. 해당 금액 내에서 랜덤 개수의 잔돈 생성
        Map<Coin, Integer> changeMap = changeService.getChangeMap(changeAmount);

        // 자판기 보유 금액 출력
        System.out.println("자판기가 보유한 동전");
        Iterator<Map.Entry<Coin, Integer>> it = changeMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Coin,Integer> i = it.next();
            int amount = i.getKey().getAmount();
            int count = i.getValue();
            System.out.println(amount +"원 - " + count + "개");
        }

//        2. 저장할 상품 정보 입력
        List<String> inputStock = InputView.readStockList();
        List<Stock> stocks = new ArrayList<>();
//            1. 상품명, 가격, 수량
        int minPrice = Integer.MAX_VALUE;
        for(String item : inputStock) {
            String[] info = item.split(",");

            System.out.println(info[0] + info[1] +info[2]);
            // 상품 최저 금액 저장
            String name = info[0].replace("[","");

            int price = Integer.parseInt(info[1]);

            if(price < minPrice) {
                minPrice = price;
            }

            int quantity = Integer.parseInt(info[2].replace("]",""));
            // 상품 정보 저장
            Stock newStock = new Stock(name,price,quantity);
            stocks.add(newStock);
        }

//        3. 투입 금액 입력
        int currentAmount = InputView.readCurrentAmount();

//        4. 구매 및 잔돈 반환 루프
        while(currentAmount > 0) {
            System.out.println("투입 금액: " + currentAmount + "원");
//            1. 투입 금액과 최저 가격 비교
            if(currentAmount < minPrice){
                break;
            }
//            2. 모든 재고 확인
            if(!isStockRemain(stocks)){
                break;
            }

//            3. 구매할 상품 명 입력
            String targetName = InputView.readTargetName();
            for(Stock stock : stocks) {
                if(stock.getName().equals(targetName) && stock.getQuantity() > 0) {
                    stock.updateQuantity();
//            4. 투입 금액에서 가격 차감
                    currentAmount -= stock.getPrice();
                }
            }

//        5. 종료
        }
//            5. 잔돈 반환
        // 남은 가격 = currentAmount
        // 잔돈 = changeMap
        System.out.println("잔돈");

//        while(currentAmount > 0) {
            Iterator<Map.Entry<Coin, Integer>> iterator = changeMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Coin, Integer> entry = iterator.next();
                Coin coin = entry.getKey();
                // 잔돈 금액
                int coinValue = coin.getAmount();

                // 저장된 랜덤 잔돈 개수
                int amount = entry.getValue();

                if (amount <= 0) {
                    continue;
                }
                int diff = currentAmount / coinValue;
                // 잔돈 개수 보다 몫이 더 적은 경우
                int count = amount;
                if (diff < amount) {
                    count = diff;
                }
                System.out.println(coin.getAmount() + "원 - " + count + "개");
                currentAmount -= count * coinValue;
                changeMap.put(coin, 0);
            }
//        }
    }
    private static boolean isStockRemain( List<Stock> stocks) {
        int sum = 0;
        for(Stock s : stocks) {
            sum += s.getQuantity();
        }
        if(sum != 0){
            return true;
        }
        return false;
    }
}
