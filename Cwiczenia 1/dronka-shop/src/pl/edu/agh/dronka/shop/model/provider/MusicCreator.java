package pl.edu.agh.dronka.shop.model.provider;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.item.Genre;
import pl.edu.agh.dronka.shop.model.item.Item;
import pl.edu.agh.dronka.shop.model.item.Music;

public class MusicCreator {
    public static Item createItem(CSVReader reader, String[] dataLine) {
        String name = reader.getValue(dataLine, "Nazwa");
        int price = Integer.parseInt(reader.getValue(dataLine, "Cena"));
        int quantity = Integer.parseInt(reader.getValue(dataLine, "Ilość"));
        String genreString = reader.getValue(dataLine, "Gatunek");
        Genre genre = Genre.valueOf(genreString.toUpperCase());
        boolean video = Boolean.parseBoolean(reader.getValue(dataLine, "Wideo"));

        return new Music(name, Category.MUSIC, price, quantity, genre, video);
    }
}
