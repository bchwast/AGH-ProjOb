package pl.edu.agh.internetshop;

import java.math.BigDecimal;

public class Product {
	
	public static final int PRICE_PRECISION = 2;
	public static final int ROUND_STRATEGY = BigDecimal.ROUND_HALF_UP;
	
    private final String name;
    private final BigDecimal price;
    private final BigDecimal discountedPrice;
    private final Discount discount;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.price.setScale(PRICE_PRECISION, ROUND_STRATEGY);
        this.discount = new Discount(0);
        this.discountedPrice = price;
    }

    public Product(String name, BigDecimal price, Discount discount) {
        this.name = name;
        this.price = price;
        this.price.setScale(PRICE_PRECISION, ROUND_STRATEGY);
        this.discount = discount;
        this.discountedPrice = applyDiscountTo(price).setScale(PRICE_PRECISION, ROUND_STRATEGY);
    }

    private BigDecimal applyDiscountTo(BigDecimal price) {
        return discount.applyTo(price);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }
}
