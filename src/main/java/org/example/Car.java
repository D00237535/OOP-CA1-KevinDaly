package org.example;

public class Car extends Vehicle {
    private int capacity;

    public Car
            (String type, String make, String model, double milesPerKwH,
             String registration, double costPerMile,
             int year, int month, int day,
             int mileage, double latitude, double longitude,
             int loadSpace) {
        super(type, make, model, milesPerKwH,
                registration, costPerMile,
                year, month, day,
                mileage, latitude, longitude);

        this.capacity = capacity;
    }

    public Car(int id, String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int loadSpace) {

        super(id, type, make, model, milesPerKwH,
                registration, costPerMile,
                year, month, day,
                mileage, latitude, longitude);

        this.capacity = capacity;
    }

    public double getLoadSpace() {
        return capacity;
    }

    public void setLoadSpace(double loadSpace) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Car{" +
                "capacity=" + capacity +
                "} " + super.toString();
    }
}

