package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Passenger {
    private int id;
    private String name;
    private String email;
    private String phone;
    private LocationGPS location;  // Home location

    private IdGenerator idGenerator = IdGenerator.getInstance("next-id-store.txt");  // get access to the id Generator

    /**
     * Constructor to be called when a new Passenger is created.
     * It uses the IdGenerator to generate a passenger id.
     */
    public Passenger(String name, String email, String phone,
                     double latitude, double longitude) {

        // some minimal validation
        if (name == null )
            throw new IllegalArgumentException("null arguments encountered");

        this.id = idGenerator.getNextId();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = new LocationGPS(latitude, longitude);
    }

    // Version of constructor called when the passenger id is known,
    // as it was read from the "passengers.txt" file.
    //
    public Passenger(int id, String name, String email, String phone,
                     double latitude, double longitude) {

        // some minimal validation
        if (name == null)
            throw new IllegalArgumentException("null arguments encountered");

        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = new LocationGPS(latitude, longitude);
    }

    public int getId() {
        return id;
    }

    private void setId() {}; // prevents the id from being set (as it should only come from autogenerator)

    public String getName() {
        return name;
    }

    public LocationGPS getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(double latitude, double longitude) {
        this.location = new LocationGPS(latitude, longitude);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"
                + "id=" + id + ", name=" + name + ", email="
                + email + ", phone=" + phone + ", location="
                + location + "}";
    }
}
