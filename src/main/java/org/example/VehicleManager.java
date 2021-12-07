package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleManager {
    final ArrayList<Vehicle> vehicleList;  // for Car and Van objects

    public VehicleManager(String fileName) {
        this.vehicleList = new ArrayList<>();
        loadVehiclesFromFile(fileName);
    }

    public ArrayList<Vehicle> getAllVehicles() {

        return this.vehicleList;
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicleList)
            System.out.println(v.toString());
    }

    public void loadVehiclesFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");
            while (sc.hasNext()) {
                int id = sc.nextInt();
                String type = sc.next();  // vehicle type
                String make = sc.next();
                String model = sc.next();
                double milesPerKwH = sc.nextDouble();
                String registration = sc.next();
                double costPerMile = sc.nextDouble();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int mileage = sc.nextInt();
                double latitude = sc.nextDouble();  // Depot GPS location
                double longitude = sc.nextDouble();
                int capacity = 0;
                double loadSpace = 0.0;

                if (type.equalsIgnoreCase("Van") ||
                        type.equalsIgnoreCase("Truck")) {
                    loadSpace = sc.nextDouble();
                } else {
                    capacity = sc.nextInt();
                }

                if (type.equalsIgnoreCase("Van") ||
                        type.equalsIgnoreCase("Truck")) {
                    // construct a Van object and add it to the passenger list
                    vehicleList.add(new Van(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpace));
                } else if (type.equalsIgnoreCase("Car") ||
                        type.equalsIgnoreCase("4X4")) {

                    vehicleList.add(new Car(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            capacity));
                }

            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);

        }


        //TODO add more functionality as per spec.
    }
    public Vehicle FindVehicleByMake(String make) {
        for (Vehicle v : vehicleList) {
            if (v.getMake().equalsIgnoreCase(make)) {
                return v;
            }
        }
        return null;
    }

    public Vehicle FindVehicleByModel(String model) {
        for (Vehicle v : vehicleList) {
            if (v.getModel().equalsIgnoreCase(model)) {
                return v;
            }
        }
        return null;
    }

    public Vehicle FindVehicleByRegNumber(String reg) {
        for (Vehicle v : vehicleList) {
            if (v.getRegistration().equalsIgnoreCase(reg)) {
                return v;
            }
        }
        return null;
    }

    public Vehicle FindVehicleByType(String type) {
        for (Vehicle v : vehicleList) {
            if (v.getType().equalsIgnoreCase(type)) {
                return v;
            }
        }
        return null;
    }

    public boolean findVehicleById(int vehicleId) {
    }
}

