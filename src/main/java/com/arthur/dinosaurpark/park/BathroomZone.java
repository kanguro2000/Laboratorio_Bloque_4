package com.arthur.dinosaurpark.park;

import com.arthur.dinosaurpark.model.Dinosaur;

public class BathroomZone extends Zone {

    private int bathroomCapacity;

    public BathroomZone(
            String name,
            int capacity,
            int bathroomCapacity) {

        super(name, capacity);

        this.bathroomCapacity = bathroomCapacity;
    }

    public int getBathroomCapacity() {
        return bathroomCapacity;
    }

    @Override
    public boolean supportsDinosaur(Dinosaur dinosaur) {

        return false;
    }

    @Override
    public String toString() {

        return "BathroomZone{" +
                "Nombre= ' " + getName() + '\'' +
                ", Capacidad de baños= " + bathroomCapacity +
                '}';
    }
}