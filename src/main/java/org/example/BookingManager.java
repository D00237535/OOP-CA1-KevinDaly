package org.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingManager {
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;


    public BookingManager(String fileName) {
        this.bookingList = new ArrayList<>();
        loadBookingsDataFromFile(fileName);
    }

    //TODO implement functionality as per specification

    public void addBooking(int bookingId, int passengerId, int vehicleId,int year,
                           int month, int day, int hour, int minute, int second,double latStart,
                           double longStart, double latEnd, double longEnd, double cost) {

        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        LocationGPS locationStart = new LocationGPS(latStart, longStart);
        LocationGPS locationEnd = new LocationGPS(latEnd, longEnd);
        Booking b1 = new Booking(bookingId, passengerId, vehicleId, year, month,
                day, hour, minute, second, latStart, longStart, latEnd, longEnd, cost);
        boolean found = false;
        for (Booking b : bookingList) {
            if (b.equals(b1)) {
                found = true;
                break;
            }
        }
        if (!found) {
            bookingList.add(b1);
        }

    }

    private void loadBookingsDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int bookingId = sc.nextInt();
                int passengerId = sc.nextInt();
                int vehicleId = sc.nextInt();
                int year = sc.nextInt();
                int month = sc.nextInt();
                int day = sc.nextInt();
                int hour = sc.nextInt();
                int minute = sc.nextInt();
                int second = sc.nextInt();
                double latStart = sc.nextDouble();
                double longStart = sc.nextDouble();
                double latEnd = sc.nextDouble();
                double longEnd = sc.nextDouble();
                double cost = sc.nextDouble();

                // construct a Booking object and add it to the booking list
                bookingList.add(new Booking(bookingId, passengerId, vehicleId, year, month, day, hour, minute, second,
                        latStart, longStart, latEnd, longEnd, cost));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    public void displayAllBookings() {
        for (Booking b : bookingList) {
            System.out.println(b.toString());
        }
    }

    public void deleteBooking(int bId) {
        for (Booking b : bookingList) {
            if (b.getBookingId() == bId) {
                bookingList.remove(b);
                break;
            }
        }
    }
}
