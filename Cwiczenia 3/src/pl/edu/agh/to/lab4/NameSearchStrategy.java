package pl.edu.agh.to.lab4;

import java.util.function.Predicate;

public class NameSearchStrategy implements Predicate<Suspect> {
    private final String name;

    public NameSearchStrategy (String name) {
        this.name = name;
    }

    @Override
    public boolean test(Suspect suspect) {
        return suspect.getFirstname().equals(name);
    }
}
