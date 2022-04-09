package pl.edu.agh.to.lab4;

public class CracovCitizen extends Suspect{

    public CracovCitizen(String name, String surname, int age) {
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public boolean canBeAccused() {
        return true;
    }
}
