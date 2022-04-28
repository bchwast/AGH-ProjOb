package pl.edu.agh.internetshop;

public class NameSearchStrategy implements SearchStrategy {
    private final String name;

    public NameSearchStrategy(String name) {
        this.name = name;
    }

    @Override
    public boolean filter(OrderLog log) {
        return log.order.getShipment().getRecipientAddress().getName().equals(name);
    }
}
