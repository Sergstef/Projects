package com.mycompany.app;

import java.util.ArrayList;

public class FlyCompany<T extends Airplane> {
    private ArrayList<T> airplaneGarage;

    public FlyCompany(ArrayList<T> airplaneGarage) {
        this.airplaneGarage = airplaneGarage;
    }

    public Airplane findAirplaneByRangeOfConsumedFuel(int minRange, int maxRange) {
        for (Airplane airplane: airplaneGarage) {
            if (airplane.getAmountOfConsumedFuel() >= minRange && airplane.getAmountOfConsumedFuel() <= maxRange) {
                return airplane;
            }
        }
        return null;
    }

    public int countTotalAirplaneCapacity() {
        int totalAirplaneCapacity = 0;
        for (Airplane airplane: airplaneGarage) {
            totalAirplaneCapacity += airplane.getAirplaneCapacity();
        }
        return totalAirplaneCapacity;
    }

    public int countTotalPayLoad() {
        int totalPayLoad = 0;
        for (Airplane airplane: airplaneGarage) {
            totalPayLoad += airplane.getPayload();
        }
        return totalPayLoad;
    }
}
