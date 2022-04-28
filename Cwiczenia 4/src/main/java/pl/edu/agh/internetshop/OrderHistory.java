package pl.edu.agh.internetshop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderHistory {
    private List<OrderLog> logs;
    private SearchStrategy strategy;

    public OrderHistory(SearchStrategy strategy) {
        this.logs = new LinkedList<>();
        this.strategy = strategy;
    }

    public SearchStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public List<OrderLog> getLogs() {
        return logs;
    }

    public void setLogs(List<OrderLog> logs) {
        this.logs = logs;
    }

    public List<OrderLog> filter() {
        return logs.stream().filter(log -> strategy.filter(log)).collect(Collectors.toCollection(ArrayList::new));
    }
}
