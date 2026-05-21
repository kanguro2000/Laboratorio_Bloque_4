package com.arthur.dinosaurpark.park;

import com.arthur.dinosaurpark.model.Dinosaur;

public class PowerPlant extends Zone {

    private double energyLevel;

    public PowerPlant(
            String name,
            int capacity,
            double energyLevel) {

        super(name, capacity);

        this.energyLevel = energyLevel;
    }

    public double getEnergyLevel() {
        return energyLevel;
    }

    public void consumeEnergy(double amount) {

        energyLevel -= amount;

        if (energyLevel < 0) {
            energyLevel = 0;
        }
    }

    public void recharge(double amount) {
        energyLevel += amount;
    }

    @Override
    public boolean supportsDinosaur(Dinosaur dinosaur) {

        return false;
    }

    @Override
    public String toString() {

        return "PowerPlant{" +
                "Nombre=' " + getName() + '\'' +
                ", Nivel de energía= " + energyLevel +
                '}';
    }
}