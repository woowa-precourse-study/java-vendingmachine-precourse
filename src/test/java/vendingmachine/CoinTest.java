package vendingmachine;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CoinTest {

    @Test
    void 생성된_price_값에_따라_동전_생성하기() {
        //given
        int price = 455;
        //when
        Coin coin = Coin.valueOf(455);
        //then
        Assertions.assertThat(coin)
                .isEqualTo(Coin.COIN_100);
    }
}
