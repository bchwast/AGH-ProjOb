package pl.edu.agh.dronka.shop.model.item;

import pl.edu.agh.dronka.shop.model.Category;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Food extends Item{
    private static final String dateFormat = "dd.MM.yyyy";
    private Date expirationDate;

    public Food(String name, Category category, int price, int quantity, Date expirationDate) {
        super(name, Category.FOOD, price, quantity);
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public Map<String, Object> getSpecialPropertiesMap() {
        return new HashMap<String, Object>() {{
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            String date = sdf.format(expirationDate);
            put("Data ważności", date);
        }};
    }
}
