package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Booking {
    private IdGenerator idGenerator = IdGenerator.getInstance("next-id-store.txt");

    private int bookingId;
    private int passengerId;
    private int vehicleId;
    private LocalDateTime bookingDateTime;
    private LocationGPS startLocation;
    private LocationGPS endLocation;

    private double cost;

    //constructor without bookingId
    public Booking(int passengerId, int vehicleId, int year, int month, int day,
                   int hour, int minute, double startLatitude, double startLongitude,
                   double endLatitude, double endLongitude, double cost) {

        this.bookingId = idGenerator.getNextId();
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingDateTime = LocalDateTime.of(year, month, day, hour, minute);
        this.startLocation = new LocationGPS(startLatitude, startLongitude);
        this.endLocation = new LocationGPS(endLatitude, endLongitude);
        this.cost = cost;
    }

    //constructor with bookingId
    public Booking(int bookingId, int passengerId, int vehicleId, int year, int month, int day,
                   int hour, int minute, double startLatitude, double startLongitude,
                   double endLatitude, double endLongitude, double cost) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingDateTime = LocalDateTime.of(year, month, day, hour, minute);
        this.startLocation = new LocationGPS(startLatitude, startLongitude);
        this.endLocation = new LocationGPS(endLatitude, endLongitude);
        this.cost = cost;
    }

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {this.bookingId = bookingId;}
    public int getPassengerId() {
        return passengerId;
    }
    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }
    public int getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }
    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }
    public LocationGPS getStartLocation() {
        return startLocation;
    }
    public void setStartLocation(LocationGPS startLocation) {
        this.startLocation = startLocation;
    }
    public LocationGPS getEndLocation() {
        return endLocation;
    }
    public void setEndLocation(LocationGPS endLocation) {
        this.endLocation = endLocation;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", passengerId=" + passengerId +
                ", vehicleId=" + vehicleId +
                ", bookingDateTime=" + bookingDateTime +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return passengerId == booking.passengerId && vehicleId == booking.vehicleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerId, vehicleId);
    }


    public void setStartLocation(double longitude, double newEndLatitude) {
    }
}
