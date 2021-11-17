package org.example;

// Class that wraps the latitude and longitude of a location
// into one object for convenience.

public class LocationGPS
{
    private double latitude;
    private double longitude;

    public LocationGPS(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public double getLatitude()
    {
        return latitude;
    }
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }
    public double getLongitude()
    {
        return longitude;
    }
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + "{latitude=" + latitude + ", longitude=" + longitude + "}";
    }



}
