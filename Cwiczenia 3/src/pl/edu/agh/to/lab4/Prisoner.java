package pl.edu.agh.to.lab4;

import java.util.Calendar;

public class Prisoner extends Suspect {
    private final int judgementYear;
    private final int senteceDuration;
    private final String pesel;


    public Prisoner(String name, String surname, String pesel, int judgementYear, int sentenceDuration) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.judgementYear = judgementYear;
        this.senteceDuration = sentenceDuration;
    }

    public boolean isJailedNow() {
        return judgementYear + senteceDuration <= getCurrentYear();
    }

    public String getPesel() {
        return pesel;
    }

    @Override
    public boolean canBeAccused() {
        return judgementYear + senteceDuration >= getCurrentYear();
    }

    private int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
