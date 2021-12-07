package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;

    public PassengerStore(String fileName) {
        this.passengerList = new ArrayList<>();
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {

        return this.passengerList;
    }

    public void displayAllPassengers() {
        for (Passenger p : this.passengerList) {
            System.out.println(p.toString());
        }
    }

    /**
     * Read Passenger records from a text file and create and add Passenger
     * objects to the PassengerStore.
     */
    private void loadPassengerDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String phone = sc.next();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                passengerList.add(new Passenger(id, name, email, phone, latitude, longitude));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }
    // TODO - see functional spec for details of code to add

    public Passenger findPassengerByName(String name) {
        for (Passenger p : passengerList) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
    public Passenger deletePassenger(int passengerId){
        Passenger pass = null;
        for (Passenger p : passengerList) {
            if (p.getId() == passengerId) {
                pass = p;
            }
        }
        passengerList.remove(pass);
        return pass;
    }


    public String addNewPassenger(String name, String email, String phone, double latitude, double longitude) {
        for (Passenger p : this.passengerList) {
            if (p.getName().toLowerCase().equals(name.toLowerCase()) &&
                    p.getEmail().toLowerCase().equals(email.toLowerCase()))
                return "\nPassenger " + name + " with email " + " is already stored";
        }
        Passenger newPassenger = new Passenger(name, email, phone, latitude, longitude);
        this.passengerList.add(newPassenger);
        return "\nPassenger \"" + name + "\" with email \"" + email + "\" has been added";
    }
        public Passenger findPassengerById(int findId) {
            for (Passenger p : passengerList)
                if(p.getId() == findId) {
                    return p;
                }
            return null;
    }
}
