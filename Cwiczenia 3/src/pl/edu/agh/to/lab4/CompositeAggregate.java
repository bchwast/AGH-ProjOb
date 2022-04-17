package pl.edu.agh.to.lab4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class CompositeAggregate implements SuspectAggregate {
    private final List<SuspectAggregate> aggregateList;

    public CompositeAggregate(List<SuspectAggregate> aggregateList) {
        this.aggregateList = aggregateList;
    }

    @Override
    public Iterator<Suspect> iterator(Predicate<Suspect> filter) {
        List<Suspect> suspects = new ArrayList<>();
        aggregateList.forEach(suspectAggregate -> {
            Iterator<Suspect> iterator = suspectAggregate.iterator(filter);
            while (iterator.hasNext()) {
                suspects.add(iterator.next());
            }
        });
        return suspects.iterator();
    }
}
