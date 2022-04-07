package pl.edu.agh.dronka.shop.model.item;

import pl.edu.agh.dronka.shop.model.Category;

import java.util.HashMap;
import java.util.Map;

public class Electronics extends Item{
    private boolean mobile;
    private boolean warranty;

    public Electronics(String name, Category category, int price, int quantity, boolean mobile, boolean warranty) {
        super(name, Category.ELECTRONICS, price, quantity);
        this.mobile = mobile;
        this.warranty = warranty;

    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    public boolean isWarranty() {
        return warranty;
    }

    public void setWarranty(boolean warranty) {
        this.warranty = warranty;
    }

    @Override
    public Map<String, Object> getSpecialPropertiesMap() {
        return new HashMap<String, Object>() {{
            put("Mobilny", mobile);
            put("Gwarancja", warranty);
        }};
    }
}
