package com.arthur.dinosaurpark.park;

import com.arthur.dinosaurpark.model.Dinosaur;

public class VisitorZone extends Zone {

    public VisitorZone(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public boolean supportsDinosaur(Dinosaur dinosaur) {

        /*
         * Visitor zones should not contain dinosaurs.
         */

        return false;
    }

    @Override
    public String toString() {

        return "VisitorZone{" +
                "Nombre= '" + getName() + '\'' +
                ", Dinosaurios= " + getDinosaurs().size() +
                '}';
    }
}