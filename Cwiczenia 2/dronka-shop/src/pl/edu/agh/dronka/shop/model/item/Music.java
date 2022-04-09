package pl.edu.agh.dronka.shop.model.item;

import pl.edu.agh.dronka.shop.model.Category;

import java.util.HashMap;
import java.util.Map;


public class Music extends Item{
    private Genre genre;
    private boolean video;

    public Music(String name, Category category, int price, int quantity, Genre genre, boolean video) {
        super(name, Category.MUSIC, price, quantity);
        this.genre = genre;
        this.video = video;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    @Override
    public Map<String, Object> getSpecialPropertiesMap() {
        return new HashMap<String, Object>() {{
            put("Gatunek", genre.getDisplayName());
            put("Posiada teledysk", video);
        }};
    }
}
