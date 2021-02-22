package com.mycompany.app;


public abstract class Airplane {
    private int airplaneCapacity;
    private int payload;
    private int flightRange;
    private String typeOfAirplane;
    private int amountOfConsumedFuel;

    public Airplane(int airplaneCapacity, int payload, int flightRange,
                    String typeOfAirplane, int amountOfConsumedFuel) {
        this.airplaneCapacity = airplaneCapacity;
        this.payload = payload;
        this.flightRange = flightRange;
        this.typeOfAirplane = typeOfAirplane;
        this.amountOfConsumedFuel = amountOfConsumedFuel;
    }

    public Airplane() {
    }

    public int getAirplaneCapacity() {
        return airplaneCapacity;
    }

    public int getPayload() {
        return payload;
    }

    public int getFlightRange() {
        return flightRange;
    }

    public String getTypeOfAirplane() {
        return typeOfAirplane;
    }

    public int getAmountOfConsumedFuel() {
        return amountOfConsumedFuel;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "airplaneCapacity=" + airplaneCapacity +
                ", payload=" + payload +
                ", flightRange=" + flightRange +
                ", typeOfAirplane='" + typeOfAirplane + '\'' +
                ", amountOfConsumedFuel=" + amountOfConsumedFuel +
                '}';
    }
}
