package pl.edu.agh.to.lab4;

import java.util.Iterator;
import java.util.function.Predicate;

public interface SuspectAggregate {
    Iterator<Suspect> iterator(Predicate<Suspect> filter);
}
