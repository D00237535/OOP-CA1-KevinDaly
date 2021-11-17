package org.example;

import java.time.LocalDateTime;

class Booking
{
    private int bookingId;
    private int passengerId;
    private int vehicleId;
    private LocalDateTime bookingDateTime;
    private LocationGPS startLocation;
    private LocationGPS endLocation;

    private double cost;  //Calculated at booking time

    //TODO - see specification

}
