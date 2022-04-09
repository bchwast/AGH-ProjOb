package pl.edu.agh.dronka.shop.model.item;

import pl.edu.agh.dronka.shop.model.Category;

import java.util.HashMap;
import java.util.Map;

public class Book extends Item {
    private int pages;
    private boolean hardCover;

    public Book(String name, Category category, int price, int quantity, int pages, boolean hardCover) {
        super(name, Category.BOOKS, price, quantity);
        this.pages = pages;
        this.hardCover = hardCover;

    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isHardCover() {
        return hardCover;
    }

    public void setHardCover(boolean hardCover) {
        this.hardCover = hardCover;
    }

    @Override
    public Map<String, Object> getSpecialPropertiesMap() {
        return new HashMap<String, Object>() {{
            put("Liczba stron", pages);
            put("Twarda oprawa", hardCover);
        }};
    }
}
