package pl.edu.agh.to.lab4;

import java.util.*;

public class Finder {
    private final CompositeAggregate compositeAggregate;
    public Finder(CompositeAggregate compositeAggregate) {
        this.compositeAggregate = compositeAggregate;
    }

    public void display(CompositeSearchStrategy searchStrategy) {
        Iterator<Suspect> suspectIterator = compositeAggregate.iterator(searchStrategy);
        List<Suspect> suspects = new ArrayList<>();

        while (suspectIterator.hasNext() && suspects.size() < 10) {
            suspects.add(suspectIterator.next());
        }

        System.out.println("Znalazlem " + suspects.size() + " pasujacych podejrzanych!");
        suspects.forEach(Suspect::display);
    }
}
