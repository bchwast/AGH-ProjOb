package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchStrategyTest {
    private final String name = "Maziarz";
    private final BigDecimal price = BigDecimal.valueOf(10);

    @Test
    public void testCorrectFilterNameStrategy() {
        // given
        OrderLog log = mock(OrderLog.class);
        log.order = mock(Order.class);
        NameSearchStrategy strategy = new NameSearchStrategy(name);

        // when
        when(log.order.getShipment()).thenReturn(mock(Shipment.class));
        when(log.order.getShipment().getRecipientAddress()).thenReturn(mock(Address.class));
        when(log.order.getShipment().getRecipientAddress().getName()).thenReturn(name);

        // then
        assertTrue(strategy.filter(log));
    }

    @Test
    public void testIncorrectFilterNameStrategy() {
        // given
        OrderLog log = mock(OrderLog.class);
        log.order = mock(Order.class);
        NameSearchStrategy strategy = new NameSearchStrategy("Kulman");

        // when
        when(log.order.getShipment()).thenReturn(mock(Shipment.class));
        when(log.order.getShipment().getRecipientAddress()).thenReturn(mock(Address.class));
        when(log.order.getShipment().getRecipientAddress().getName()).thenReturn(name);

        // then
        assertFalse(strategy.filter(log));
    }

    @Test
    public void testCorrectFilterPriceStrategy() {
        // given
        OrderLog log = mock(OrderLog.class);
        log.order = mock(Order.class);
        PriceSearchStrategy strategy = new PriceSearchStrategy(price);

        // when
        when(log.order.getPrice()).thenReturn(price);

        // then
        assertTrue(strategy.filter(log));
    }

    @Test
    public void testIncorrectFilterPriceStrategy() {
        // given
        OrderLog log = mock(OrderLog.class);
        log.order = mock(Order.class);
        PriceSearchStrategy strategy = new PriceSearchStrategy(BigDecimal.valueOf(500));

        // when
        when(log.order.getPrice()).thenReturn(price);

        // then
        assertFalse(strategy.filter(log));
    }

    @Test
    public void testAllTrue() {
        // given
        CompositeSearchStrategy strategy = new CompositeSearchStrategy();
        SearchStrategy strategy1 = mock(SearchStrategy.class);
        SearchStrategy strategy2 = mock(SearchStrategy.class);
        SearchStrategy strategy3 = mock(SearchStrategy.class);
        OrderLog log = mock(OrderLog.class);

        // when
        when(strategy1.filter(log)).thenReturn(true);
        when(strategy2.filter(log)).thenReturn(true);
        when(strategy3.filter(log)).thenReturn(true);

        strategy.addSearchStrategy(strategy1);
        strategy.addSearchStrategy(strategy2);
        strategy.addSearchStrategy(strategy3);

        // then
        assertTrue(strategy.filter(log));
    }

    @Test
    public void testSomeFalse() {
        // given
        CompositeSearchStrategy strategy = new CompositeSearchStrategy();
        SearchStrategy strategy1 = mock(SearchStrategy.class);
        SearchStrategy strategy2 = mock(SearchStrategy.class);
        SearchStrategy strategy3 = mock(SearchStrategy.class);
        SearchStrategy strategy4 = mock(SearchStrategy.class);
        OrderLog log = mock(OrderLog.class);

        // when
        when(strategy1.filter(log)).thenReturn(true);
        when(strategy2.filter(log)).thenReturn(false);
        when(strategy3.filter(log)).thenReturn(true);
        when(strategy4.filter(log)).thenReturn(false);

        strategy.addSearchStrategy(strategy1);
        strategy.addSearchStrategy(strategy2);
        strategy.addSearchStrategy(strategy3);
        strategy.addSearchStrategy(strategy4);

        // then
        assertFalse(strategy.filter(log));
    }
}
