package com.arthur.dinosaurpark.park;

import com.arthur.dinosaurpark.model.Dinosaur;
import com.arthur.dinosaurpark.model.HerbivoreDinosaur;

public class HerbivoreZone extends Zone {

    public HerbivoreZone(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public boolean supportsDinosaur(Dinosaur dinosaur) {
        return dinosaur instanceof HerbivoreDinosaur;
    }

    @Override
    public String toString() {

        return "HerbivoreZone{" +
                "Nombre='" + getName() + '\'' +
                ", Dinosaurios= " + getDinosaurs().size() +
                '}';
    }
}