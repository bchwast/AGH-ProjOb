package pl.edu.agh.to.lab4;

abstract public class Suspect {
    private final String firstname;
    private final String lastname;
    private final int age;

    public Suspect(String firstname, String lastname, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String display() {
        return firstname + " " + lastname;
    }

    public abstract boolean canBeAccused();

    public int getAge() {
        return age;
    }
}
