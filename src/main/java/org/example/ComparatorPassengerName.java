package org.example;
import java.util.Comparator;
public class ComparatorPassengerName implements Comparator<Passenger>{
    @Override
    public int compare(Passenger v1, Passenger v2)
    {
        return v1.getName().compareTo(v2.getName());
    }
}
