package pl.edu.agh.dronka.shop.model.provider;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.item.Book;
import pl.edu.agh.dronka.shop.model.item.Item;

public class BookCreator {
    public static Item createItem(CSVReader reader, String[] dataLine) {
        String name = reader.getValue(dataLine, "Nazwa");
        int price = Integer.parseInt(reader.getValue(dataLine, "Cena"));
        int quantity = Integer.parseInt(reader.getValue(dataLine, "Ilość"));
        int pages = Integer.parseInt(reader.getValue(dataLine,"Liczba stron"));
        boolean hardCover = Boolean.parseBoolean(reader.getValue(dataLine,"Twarda oprawa"));

        return new Book(name, Category.BOOKS, price, quantity, pages, hardCover);
    }
}
