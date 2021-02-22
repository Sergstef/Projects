package com.mycompany.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App
{
    public static void main( String[] args ) {
        Airplane airbus = new Airbus(200, 1000, 200, "Airbus-37", 1000);
        Airplane airbus2 = new Airbus(200, 1000, 100, "Airbus-37", 500);
        Airplane airbus3 = new Airbus(200, 1000, 50, "Airbus-37", 365);
        Airplane boeing1 = new Boeing(200, 1000, 150, "Boeing-37", 200);
        ArrayList<Airplane> arr = new ArrayList<>();
        arr.add(airbus);
        arr.add(airbus2);
        arr.add(airbus3);
        arr.sort(new AirplaneComparator());
        System.out.println(arr);
        arr.add(boeing1);
        FlyCompany flyCompany = new FlyCompany(arr);
        System.out.println(flyCompany.findAirplaneByRangeOfConsumedFuel(100, 300));
        System.out.println(flyCompany.countTotalAirplaneCapacity());
        System.out.println(flyCompany.countTotalPayLoad());
    }
}
