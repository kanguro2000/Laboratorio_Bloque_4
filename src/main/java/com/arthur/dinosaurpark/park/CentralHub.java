package com.arthur.dinosaurpark.park;

import com.arthur.dinosaurpark.model.Dinosaur;

public class CentralHub extends Zone {

    public CentralHub(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public boolean supportsDinosaur(Dinosaur dinosaur) {

        /*
         * Central hub should not contain dinosaurs
         */

        return false;
    }

    @Override
    public String toString() {

        return "CentralHub{" +
                "Nombre=' " + getName() + '\'' +
                ", Turistas= " + getTourists().size() +
                '}';
    }
}