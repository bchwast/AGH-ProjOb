package pl.edu.agh.internetshop;

import java.math.BigDecimal;

public class Discount {
    private final BigDecimal discountPercentage;

    public Discount(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException();
        }
        this.discountPercentage = BigDecimal.valueOf((100 - (double) percentage) / 100);
    }

    public BigDecimal applyTo(BigDecimal price) {
        return price.multiply(discountPercentage);
    }
}
