package pl.edu.agh.to.lab4;

public class CracovCitizen extends Suspect {
    public CracovCitizen(String firstname, String lastname, int age) {
        super(firstname, lastname, age);
    }

    @Override
    public boolean canBeAccused() {
        return true;
    }
}
