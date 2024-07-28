package fb.fb4.pk1jfx;

import java.util.Comparator;

public class RisikoComparator implements Comparator<Risiko> {
    @Override
    public int compare(Risiko o1, Risiko o2) {
        return Float.compare(o1.berechneRisikowert(), o2.berechneRisikowert());
    }
}
