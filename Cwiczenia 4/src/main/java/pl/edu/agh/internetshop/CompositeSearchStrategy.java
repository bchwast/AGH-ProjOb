package pl.edu.agh.internetshop;

import java.util.ArrayList;
import java.util.List;

public class CompositeSearchStrategy implements SearchStrategy {
    private List<SearchStrategy> strategies;

    public CompositeSearchStrategy() {
        this.strategies = new ArrayList<>();
    }

    public void addSearchStrategy(SearchStrategy strategy) {
        strategies.add(strategy);
    }

    @Override
    public boolean filter(OrderLog log) {
        return strategies.stream().allMatch(strategy -> strategy.filter(log));
    }
}
