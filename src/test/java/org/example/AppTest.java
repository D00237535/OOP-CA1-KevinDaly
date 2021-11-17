package org.example;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for Vehicles App.
 */
public class AppTest 
{
    // Test construction of Van object.
    @Test
    public void createVan()
    {
        Van van = new Van("Truck","Nissan","Urvan",4,"181MN6538107",
                6.00,2021,05,24,126000,53.2543,-6.4444,240);
        assertEquals("Nissan", van.getMake());
        assertEquals("Urvan", van.getModel());
        assertEquals("181MN6538107", van.getRegistration());
        assertEquals(6.00, van.getCostPerMile(),0.005);
        assertEquals(2021, van.getLastServicedDate().getYear());
        assertEquals(5, van.getLastServicedDate().getMonthValue());
        assertEquals(24, van.getLastServicedDate().getDayOfMonth());
        assertEquals(126000,van.getMileage());
        assertEquals(53.2543, van.getDepotGPSLocation().getLatitude(),0.00005);
        assertEquals(-6.4444, van.getDepotGPSLocation().getLongitude(),0.00005);
        assertEquals(240,van.getLoadSpace(),0.05);
    }
}
