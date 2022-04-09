package pl.edu.agh.dronka.shop.model.provider;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.item.Electronics;
import pl.edu.agh.dronka.shop.model.item.Item;

public class ElectronicsCreator {
    public static Item createItem(CSVReader reader, String[] dataLine) {
        String name = reader.getValue(dataLine, "Nazwa");
        int price = Integer.parseInt(reader.getValue(dataLine, "Cena"));
        int quantity = Integer.parseInt(reader.getValue(dataLine, "Ilość"));
        boolean mobile = Boolean.parseBoolean(reader.getValue(dataLine, "Mobilny"));
        boolean warranty = Boolean.parseBoolean(reader.getValue(dataLine, "Gwarancja"));

        return new Electronics(name, Category.ELECTRONICS, price, quantity, mobile, warranty);
    }
}
