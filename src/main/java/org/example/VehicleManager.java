package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class VehicleManager {
    private final ArrayList<Vehicle> vehicleList;  // for Car and Van objects
    private PassengerStore passengerStore;
    private BookingManager bookingManager;

    public VehicleManager(String fileName, PassengerStore passengerStore, BookingManager bookingManager) {
        this.vehicleList = new ArrayList<>();
        this.passengerStore = passengerStore;
        this.bookingManager = bookingManager;
        loadVehiclesFromFile(fileName);
    }

    public List<Vehicle> getAllVehicles() {
        return this.vehicleList;
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicleList)
            System.out.println(v.toString());
    }

    public void displayAllVehicleId() {
        for (Vehicle v : vehicleList)
            System.out.println(v.getId());
    }

    public void loadVehiclesFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String type = sc.next();    // vehicle type
                String make = sc.next();    // vehicle brand
                String model = sc.next();   // e.g Mustang
                double milesPerKwH = sc.nextDouble();
                String registration = sc.next();
                double costPerMile = sc.nextDouble();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int mileage = sc.nextInt();
                double latitude = sc.nextDouble();  // Depot GPS location
                double longitude = sc.nextDouble();
                if(type.equalsIgnoreCase("Van") || type.equalsIgnoreCase("Truck"))
                {
                    double loadSpace = sc.nextDouble();
                    vehicleList.add(new Van(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpace));
                }
                else if (type.equalsIgnoreCase("Car")) {
                    int numberOfSeats = sc.nextInt();

                    // construct a Car object and add it to the passenger list
                    vehicleList.add(new Car(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            numberOfSeats));

                }
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    //TODO add more functionality as per spec.

    public void displayVehicleMenu() {
        final String MENU_ITEMS = "\n*** VEHICLE MENU ***\n"
                + "1. Show all Vehicles\n"
                + "2. Find Vehicle by Type\n"
                + "3. Find Vehicle by Registration\n"
                + "4. Add Vehicle\n"
                + "5. Exit\n"

                + "Enter Option [1,5]";

        final int SHOW_ALL = 1;
        final int FIND_BY_TYPE = 2;
        final int FIND_BY_REGISTRATION = 3;
        final int ADD_NEW_VEHICLE = 4;
        final int EXIT = 5;

        ArrayList<Vehicle> vehicles;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {

                    case SHOW_ALL:
                        System.out.println("Display ALL Vehicles");
                        displayAllVehicles();
                        break;

                    case ADD_NEW_VEHICLE:
                        System.out.println("Add Vehicle Chosen");
                        addNewVehicle();
                        break;

                    case FIND_BY_TYPE:
                        System.out.println("Find Vehicles by Type");
                        System.out.println("Enter Vehicle Type: ");
                        String type = keyboard.nextLine();
                        vehicles = findVehiclesByType(type);
                        if (vehicles == null)
                            System.out.println("No Vehicles matching the type \"" + type + "\"");
                        else
                            System.out.println("Found Vehicle: \n" + vehicles);
                        break;

                    case FIND_BY_REGISTRATION:
                        System.out.println("Find Vehicles by Registration");
                        System.out.println("Enter Vehicle Registration: ");
                        String registration = keyboard.nextLine();
                        vehicles = findVehiclesByRegistration(registration);
                        if (vehicles == null)
                            System.out.println("No Vehicles matching the registration \"" + registration + "\"");
                        else
                            System.out.println("Found Vehicle: \n" + vehicles);
                        break;

                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }



    CarRegistrationComparator registrationComparator = new CarRegistrationComparator();

    public ArrayList<Vehicle> findVehiclesByRegistration(String reg) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle v : vehicleList) {
            if (v.getRegistration().equalsIgnoreCase(reg)) {
                vehicles.add(v);
            }
        }
        return vehicles;
    }
    public ArrayList<Vehicle> findVehicleById(int findId) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle v : vehicleList) {
            if (v.getId() == findId) {
                vehicles.add(v);
            }
        }
        return vehicles;
    }

    public ArrayList<Vehicle> findVehiclesByType(String type) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            if (v.getType().equalsIgnoreCase(type)) {
                vehicles.add(v);
            }
        }

        Collections.sort(vehicles, registrationComparator);
        return vehicles;
    }

    public static class CarRegistrationComparator implements Comparator<Vehicle> {

        public int compare(Vehicle v1, Vehicle v2) {
            return v1.getRegistration().compareTo(v2.getRegistration());
        }
    }

    public void addNewVehicle(String type, String make, String model, double milesPerKwH,
                              String registration, double costPerMile,
                              int year, int month, int day,
                              int mileage, double latitude, double longitude, int loadSpace) {

        if (type.equalsIgnoreCase("Van") ||
                type.equalsIgnoreCase("Truck")) {// construct a Van object and add it to the passenger list

            vehicleList.add(new Van(type, make, model, milesPerKwH,
                    registration, costPerMile,
                    year, month, day,
                    mileage, latitude, longitude,
                    loadSpace));
        }
        else if (type.equalsIgnoreCase("Car") ||
                type.equalsIgnoreCase("4x4")){// construct a Car object and add it to the passenger list
            vehicleList.add(new Car(type, make, model, milesPerKwH,
                    registration, costPerMile,
                    year, month, day,
                    mileage, latitude, longitude,
                    loadSpace));
            //loadspace is passed in as int for number of seats
        }
    }

    public void addNewVehicle() {

        double additional;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter Type:");
            String type = keyboard.nextLine();

        System.out.println("Enter Make:");
            String make = keyboard.nextLine();

        System.out.println("Enter Model:");
            String model = keyboard.nextLine();

        System.out.println("Enter milesPerKwH");
            String usersInput = keyboard.nextLine();
            double milesPerKwH = Double.parseDouble(usersInput);

        System.out.println("Enter Registration");
            String registration = keyboard.nextLine();

        System.out.println("Enter Cost Per Mile");
            double costPerMile = keyboard.nextDouble();

        System.out.println("Enter Last Service Year");
            int year = keyboard.nextInt();

        System.out.println("Enter Last Service Month");
            int month = keyboard.nextInt();

        System.out.println("Enter Last Service Day");
            int day = keyboard.nextInt();

        System.out.println("Enter Mileage");
            int mileage = keyboard.nextInt();

        System.out.println("Enter Latitude");
            double latitude = keyboard.nextDouble();

        System.out.println("Enter longitude");
            double longitude = keyboard.nextDouble();


        if (type.equalsIgnoreCase("Van") || type.equalsIgnoreCase("Truck")) {
            System.out.println("Enter LoadSpace");
            additional = keyboard.nextDouble();
        } else {
            System.out.println("Enter Number of Seats");
            additional = keyboard.nextInt();
        }

        try {
            addNewVehicle(type, make, model, milesPerKwH, registration, costPerMile, year, month, day, mileage, latitude, longitude, (int) additional);
            System.out.println("Vehicle added");

        } catch (InputMismatchException | NumberFormatException e) {
            System.out.print("Invalid option - please enter valid details");
        }
    }

    public void saveVehiclesToFile(String fileName) {
        try {
            FileWriter vehicleWriter = new FileWriter(fileName);
            for (Vehicle v : vehicleList) {

                if (v instanceof Car) {
                    vehicleWriter.write(
                            v.getId() + "," +
                                    v.getType() + "," +
                                    v.getMake() + "," +
                                    v.getModel() + ","
                                    + v.getMilesPerKwH() + ","
                                    + v.getRegistration() + ","
                                    + v.getCostPerMile() + ","
                                    + v.getLastServicedDate().getYear() + ","
                                    + v.getLastServicedDate().getMonthValue() + ","
                                    + v.getLastServicedDate().getDayOfMonth() + ","
                                    + v.getMileage() + ","
                                    + v.getDepotGPSLocation().getLatitude() + ","
                                    + v.getDepotGPSLocation().getLongitude() + "," +
                                    ((Car) v).getNumberOfSeats() +
                                    "\n"
                    );

                } else if (v instanceof Van) {
                    vehicleWriter.write(
                            v.getId() + "," +
                                    v.getType() + "," +
                                    v.getMake() + "," +
                                    v.getModel() + ","
                                    + v.getMilesPerKwH() + ","
                                    + v.getRegistration() + ","
                                    + v.getCostPerMile() + ","
                                    + v.getLastServicedDate().getYear() + ","
                                    + v.getLastServicedDate().getMonthValue() + ","
                                    + v.getLastServicedDate().getDayOfMonth() + ","
                                    + v.getMileage() + ","
                                    + v.getDepotGPSLocation().getLatitude() + ","
                                    + v.getDepotGPSLocation().getLongitude() + "," +
                                    ((Van) v).getLoadSpace() +
                                    "\n"
                    );
                }
            }
            vehicleWriter.close();
            System.out.println("The Vehicle has be written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
