package org.example;
import java.util.Comparator;
public class ComparatorBookingDateTime implements Comparator<Booking>{
    @Override
    public int compare(Booking b1, Booking b2)
    {
        return b1.getBookingDateTime().compareTo(b2.getBookingDateTime());
    }
}