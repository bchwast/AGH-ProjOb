package pl.edu.agh.to.lab4;

import java.util.Calendar;

public class Prisoner extends Suspect {
    private final int judgementYear;

    private final int senteceDuration;

    private final String pesel;

    public Prisoner(String firstname, String lastname, String pesel, int judgementYear, int sentenceDuration, int age) {
        super(firstname, lastname, age);
        this.pesel = pesel;
        this.judgementYear = judgementYear;
        this.senteceDuration = sentenceDuration;
    }

    public String getPesel() {
        return pesel;
    }

    @Override
    public boolean canBeAccused() {
        return judgementYear + senteceDuration < getCurrentYear();
    }

    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public boolean isJailedNow() {
        return judgementYear + senteceDuration > getCurrentYear();
    }
}
