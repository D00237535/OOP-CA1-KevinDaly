package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

class Booking
{
    private IdGenerator idGenerator = IdGenerator.getInstance("next-id-store.txt");
    private int bookingId;
    private int passengerId;
    private int vehicleId;
    private LocalDate bookingDate;
    private LocationGPS startLocation;
    private LocationGPS endLocation;

    private double cost;  //Calculated at booking time

    //TODO - see specification

    public Booking(int bookingId, int passengerId, int vehicleId, int year, int month, int day,
                   double latStart, double longStart,double latEnd, double longEnd, double cost) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingDate = LocalDate.of(year, month, day);
        this.startLocation = new LocationGPS(latStart,longStart);
        this.endLocation = new LocationGPS(latEnd,longEnd);
        this.cost = cost;
    }
    public Booking(int passengerId, int vehicleId, int year, int month, int day,
                   double latStart, double longStart,double latEnd, double longEnd, double cost) {
        this.bookingId = idGenerator.getNextId();
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingDate = LocalDate.of(year, month, day);
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
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return passengerId == booking.passengerId && vehicleId == booking.vehicleId && bookingDate.equals(booking.bookingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerId, vehicleId, bookingDate);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", passengerId=" + passengerId +
                ", vehicleId=" + vehicleId +
                ", bookingDateTime=" + bookingDate +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", cost=" + cost +
                '}';
    }
}
