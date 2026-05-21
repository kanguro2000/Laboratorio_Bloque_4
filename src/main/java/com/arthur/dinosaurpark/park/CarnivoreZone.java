package com.arthur.dinosaurpark.park;

import com.arthur.dinosaurpark.model.CarnivoreDinosaur;
import com.arthur.dinosaurpark.model.Dinosaur;

public class CarnivoreZone extends Zone {

    public CarnivoreZone(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public boolean supportsDinosaur(Dinosaur dinosaur) {
        return dinosaur instanceof CarnivoreDinosaur;
    }
}