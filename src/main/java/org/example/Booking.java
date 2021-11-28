//package org.example;
//
//import java.time.LocalDateTime;
//
//class Booking
//{
//    private int bookingId;
//    private int passengerId;
//    private int vehicleId;
//    private LocalDateTime bookingDateTime;
//    private LocationGPS startLocation;
//    private LocationGPS endLocation;
//
//    private double cost;  //Calculated at booking time
//
//    //TODO - see specification
//
//    public Booking(int bookingId, int passengerId, int vehicleId, int year, int month, int day, int hour, int minute, int second,
//                   double latStart, double longStart,double latEnd, double longEnd, double cost) {
//        this.bookingId = bookingId;
//        this.passengerId = passengerId;
//        this.vehicleId = vehicleId;
//        this.bookingDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
//        this.startLocation = new LocationGPS(latStart,longStart);
//        this.endLocation = new LocationGPS(latEnd,longEnd);
//        this.cost = cost;
//    }
//
//    public int getBookingId() return bookingId;
//    public void setBookingId(int bookingId) {this.bookingId = bookingId;}
//    public int getPassengerId() {return passengerId;}
//    public void setPassengerId(int passengerId) {this.passengerId = passengerId;}
//    public int getVehicleId() {return vehicleId;}
//    public void setVehicleId(int vehicleId) {this.vehicleId = vehicleId;}
//    public LocalDateTime getBookingDateTime() {return bookingDateTime;}
//    public void setBookingDateTime(LocalDateTime bookingDateTime) {this.bookingDateTime = bookingDateTime;}
//    public LocationGPS getStartLocation() {return startLocation;}
//    public void setStartLocation(LocationGPS startLocation) {this.startLocation = startLocation;}
//    public LocationGPS getEndLocation() {return endLocation;}
//    public void setEndLocation(LocationGPS endLocation) {this.endLocation = endLocation;}
//    public double getCost() {return cost;}
//    public void setCost(double cost) {this.cost = cost;}
//
//    @Override
//    public String toString() {
//        return "Booking{" +
//                "bookingId=" + bookingId +
//                ", passengerId=" + passengerId +
//                ", vehicleId=" + vehicleId +
//                ", bookingDateTime=" + bookingDateTime +
//                ", startLocation=" + startLocation +
//                ", endLocation=" + endLocation +
//                ", cost=" + cost +
//                '}';
//    }
//}




