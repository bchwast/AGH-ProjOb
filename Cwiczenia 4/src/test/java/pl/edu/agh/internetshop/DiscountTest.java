package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static pl.edu.agh.internetshop.util.CustomAssertions.assertBigDecimalCompareValue;

public class DiscountTest {
    private final String name = "test";
    private final BigDecimal price = BigDecimal.valueOf(10);

    @Test
    public void testPriceWithTooSmallDiscount() {
        // given, when, then
        assertThrows(IllegalArgumentException.class, () -> new Product(name, price, new Discount(-5)));
    }

    @Test
    public void testPriceWithNoDiscount() {
        // given, when
        Product product = new Product(name, price);

        // then
        assertBigDecimalCompareValue(price, product.getDiscountedPrice());
    }

    @Test
    public void testPriceWithDiscount() {
        // given, when
        Product product = new Product(name, price, new Discount(50));

        // then
        assertBigDecimalCompareValue(BigDecimal.valueOf(5), product.getDiscountedPrice());
    }

    @Test
    public void testPriceWithFullDiscount() {
        // given, when
        Product product = new Product(name, price, new Discount(100));

        // then
        assertBigDecimalCompareValue(BigDecimal.valueOf(0), product.getDiscountedPrice());
    }

    @Test
    public void testPriceWithTooBigDiscount() {
        // given, when, then
        assertThrows(IllegalArgumentException.class, () -> new Product(name, price, new Discount(105)));
    }

    @Test
    public void testProductDiscountInOrder() {
        // given
        Product product1 = mock(Product.class);
        given(product1.getDiscountedPrice()).willReturn(BigDecimal.valueOf(10));
        Product product2 = mock(Product.class);
        given(product2.getDiscountedPrice()).willReturn(BigDecimal.valueOf(5));

        // wnen
        Order order = new Order(Arrays.asList(product1, product2));

        // then
        assertBigDecimalCompareValue(BigDecimal.valueOf(15), order.getPriceWithProductDiscount());
    }

    @Test
    public void testOrderDiscount() {
        // given
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        given(product1.getDiscountedPrice()).willReturn(BigDecimal.valueOf(10));
        given(product2.getDiscountedPrice()).willReturn(BigDecimal.valueOf(5));

        // when
        Order order = new Order(Arrays.asList(product1, product2), new Discount(50));

        // then
        assertBigDecimalCompareValue(BigDecimal.valueOf(7.5), order.getPriceWithOrderDiscount());
    }

    @Test
    public void testOrderDiscountRoundUp() {
        // given
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        given(product1.getDiscountedPrice()).willReturn(BigDecimal.valueOf(5));
        given(product2.getDiscountedPrice()).willReturn(BigDecimal.valueOf(0.7));

        // when
        Order order = new Order(Arrays.asList(product1, product2), new Discount(5));

        // then
        assertBigDecimalCompareValue(BigDecimal.valueOf(5.42), order.getPriceWithOrderDiscount());
    }

}
