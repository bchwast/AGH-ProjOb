package pl.edu.agh.dronka.shop.model.provider;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.item.Food;
import pl.edu.agh.dronka.shop.model.item.Genre;
import pl.edu.agh.dronka.shop.model.item.Item;
import pl.edu.agh.dronka.shop.model.item.Music;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodCreator {
    private static final String dateFormat = "dd.MM.yy";

    public static Item createItem(CSVReader reader, String[] dataLine) {
        String name = reader.getValue(dataLine, "Nazwa");
        int price = Integer.parseInt(reader.getValue(dataLine, "Cena"));
        int quantity = Integer.parseInt(reader.getValue(dataLine, "Ilość"));
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String date = reader.getValue(dataLine, "Data przydatności do spożycia");

        Date expirationDate;
        try {
            expirationDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            expirationDate = new Date(0);
        }

        return new Food(name, Category.FOOD, price, quantity, expirationDate);
    }
}
