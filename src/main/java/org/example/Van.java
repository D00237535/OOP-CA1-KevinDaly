package org.example;

// Van class to represent Vans and Trucks
//
public class Van extends Vehicle
{
    private double loadSpace;   // measured in litres.  For Vans and Trucks

    public Van(String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int loadSpace)
    {
        // call superclass constructor to initialize the fields defined in Vehicle
        super(type,make,model,milesPerKwH,
                registration,costPerMile,
                year,month,day,
                mileage,latitude,longitude);

        this.loadSpace = loadSpace;
    }

    // Constructor version to be used to recreate a Van that was read from file.
    // It will have already been allocated an id.
    //
    public Van(int id, String type, String make, String model, double milesPerKwH,
               String registration, double costPerMile,
               int year, int month, int day,
               int mileage, double latitude, double longitude,
               int loadSpace)
    {
        // call superclass constructor to initialize the fields defined in Vehicle
        super(id,type,make,model,milesPerKwH,
                registration,costPerMile,
                year,month,day,
                mileage,latitude,longitude);

        this.loadSpace = loadSpace;
    }

    public double getLoadSpace() {
        return loadSpace;
    }
    public void setLoadSpace(double loadSpace) {
        this.loadSpace = loadSpace;
    }

    @Override
    public String toString() {
        return "Van{" +
                "loadSpace=" + loadSpace +
                "} " + super.toString();
    }
}
