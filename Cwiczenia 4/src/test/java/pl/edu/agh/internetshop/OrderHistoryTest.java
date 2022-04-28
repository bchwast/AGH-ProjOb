package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderHistoryTest {

    @Test
    public void testFilter() {
        // given
        SearchStrategy strategy = mock(SearchStrategy.class);
        OrderHistory history = new OrderHistory(strategy);
        OrderLog log1 = mock(OrderLog.class);
        OrderLog log2 = mock(OrderLog.class);
        OrderLog log3 = mock(OrderLog.class);
        OrderLog log4 = mock(OrderLog.class);
        ArrayList<OrderLog> logs = new ArrayList<>(List.of(log1, log2, log3, log4));

        // when
        when(strategy.filter(log1)).thenReturn(true);
        when(strategy.filter(log2)).thenReturn(false);
        when(strategy.filter(log3)).thenReturn(true);
        when(strategy.filter(log4)).thenReturn(false);
        history.setStrategy(strategy);
        history.setLogs(logs);
        List<OrderLog> result = history.filter();

        // then
        assertEquals(2, result.size());
        assertEquals(log1, result.get(0));
        assertEquals(log3, result.get(1));
    }
}
