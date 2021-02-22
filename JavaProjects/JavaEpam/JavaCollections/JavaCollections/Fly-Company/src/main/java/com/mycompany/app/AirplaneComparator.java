package com.mycompany.app;

import java.util.Comparator;

public class AirplaneComparator implements Comparator<Airplane> {
    @Override
    public int compare(Airplane o1, Airplane o2) {
        return o1.getFlightRange() - (o2.getFlightRange());
    }
}
