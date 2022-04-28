package pl.edu.agh.internetshop;

public class ProductNameSearchStrategy implements SearchStrategy{
    private final String name;

    public ProductNameSearchStrategy(String name) {
        this.name = name;
    }

    @Override
    public boolean filter(OrderLog log) {
        return log.order.getProducts().stream().anyMatch(product -> product.getName().equals(name));
    }
}
