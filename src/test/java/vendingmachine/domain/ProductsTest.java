package vendingmachine.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductsTest {

    Products products;

    @BeforeEach
    void setUp() {
        Product cola = Product.of("콜라", 1500, 0);
        Product soda = Product.of("사이다", 1000, 0);

        products = Products.from(List.of(cola, soda));
    }

    @Test
    void product의_재고가_없는지_확인할_수_있다() {
        //given
        //when
        boolean soldOut = products.isSoldOut();
        //then
        Assertions.assertThat(soldOut)
                .isTrue();
    }

    @Test
    void price가_products의상품을_살_수_없을때_canBuy는_false를_반환한다() {
        //given
        int price = 900;
        //when
        boolean canBuy = products.canBuy(price);
        //then
        Assertions.assertThat(canBuy)
                .isFalse();
    }

}
