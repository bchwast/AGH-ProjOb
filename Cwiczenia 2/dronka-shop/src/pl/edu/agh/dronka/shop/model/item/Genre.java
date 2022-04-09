package pl.edu.agh.dronka.shop.model.item;

import pl.edu.agh.dronka.shop.model.Category;

public enum Genre {

    POP("Pop"), ROCK("Rock"), METAL("Metal"), TECHNO("Techno"), HOUSE("House"), CLASSICAL("Klasyczna"), RAP("Rap");

    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    private Genre(String displayName) {
        this.displayName = displayName;
    }
}
