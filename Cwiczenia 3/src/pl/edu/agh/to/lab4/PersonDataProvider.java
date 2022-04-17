package pl.edu.agh.to.lab4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public class PersonDataProvider implements SuspectAggregate {

    private final Collection<CracovCitizen> cracovCitizens = new ArrayList<CracovCitizen>();

    public PersonDataProvider() {
    }

    public Collection<CracovCitizen> getAllCracovCitizens() {
        return cracovCitizens;
    }

    @Override
    public Iterator<Suspect> iterator(Predicate<Suspect> filter) {
        return new SuspectIterator(cracovCitizens.iterator(), filter);
    }
}
