package pl.edu.agh.dronka.shop.model.provider;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.item.Item;
import pl.edu.agh.dronka.shop.model.item.Sport;

public class SportCreator {

    public static Item createItem(CSVReader reader, String[] dataLine) {
        String name = reader.getValue(dataLine, "Nazwa");
        int price = Integer.parseInt(reader.getValue(dataLine, "Cena"));
        int quantity = Integer.parseInt(reader.getValue(dataLine, "Ilość"));

        return new Sport(name, Category.SPORT, price, quantity);
    }
}
