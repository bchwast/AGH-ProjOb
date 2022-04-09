package pl.edu.agh.to.lab4;

public abstract class Suspect {
    protected String name;

    protected String surname;

    protected int age;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String display() {
        return name + " " + surname;
    }

    public int getAge() {
        return age;
    }

    public abstract boolean canBeAccused();
}
