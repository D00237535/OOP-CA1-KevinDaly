package org.example;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public Booking(int bookingId, int passengerId, int vehicleId, int year, int month, int day, int hour, int minute, int second,
                   double latStart, double longStart,double latEnd, double longEnd, double cost) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        this.startLocation = new LocationGPS(latStart,longStart);
        this.endLocation = new LocationGPS(latEnd,longEnd);
        this.cost = cost;
    }

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
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
        return bookingId == booking.bookingId && passengerId == booking.passengerId && vehicleId == booking.vehicleId && Double.compare(booking.cost, cost) == 0 && Objects.equals(bookingDateTime, booking.bookingDateTime) && Objects.equals(startLocation, booking.startLocation) && Objects.equals(endLocation, booking.endLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, passengerId, vehicleId, bookingDateTime, startLocation, endLocation, cost);
    }
}