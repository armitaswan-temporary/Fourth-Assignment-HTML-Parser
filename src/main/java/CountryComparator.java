import java.util.Collections;
import java.util.Comparator;

public class CountryComparator implements Comparator<Country> {
    private int basedON;

    // getting what is the comparing based on
    public CountryComparator(int basedOn) {
        this.basedON = basedOn;
    }
    @Override
    public int compare(Country c1, Country c2) {
        if (basedON == 1) {
            return c1.getName().compareTo(c2.getName());
        }
        else if (basedON == 2) {
            return Integer.compare(c1.getPopulation(), c2.getPopulation());
        }
        else {
            return Double.compare(c1.getArea(), c2.getArea());
        }
    }
}