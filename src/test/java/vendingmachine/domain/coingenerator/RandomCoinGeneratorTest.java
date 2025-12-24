package vendingmachine.domain.coingenerator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import vendingmachine.Coin;
import vendingmachine.domain.CoinGenerator;
import vendingmachine.domain.Coins;

class RandomCoinGeneratorTest {
    CoinGenerator generator = new RandomCoinGenerator();

    @Test
    void price_값을_넣으면_coins를_생성한다() {
        //given
        int price = 10;
        //when
        Coins coins = generator.generate(price);
        //then
        Assertions.assertThat(coins.getCoins())
                .hasSize(1)
                .extracting(coin -> coin.get(Coin.valueOf(price)))
                .isEqualTo(1);
    }
}
