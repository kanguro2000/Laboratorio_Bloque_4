package com.arthur.dinosaurpark.park;

import com.arthur.dinosaurpark.model.Dinosaur;

public class ObservationEnclosure extends Zone {

    private int securityLevel;

    public ObservationEnclosure(
            String name,
            int capacity,
            int securityLevel) {

        super(name, capacity);

        this.securityLevel = securityLevel;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    @Override
    public boolean supportsDinosaur(Dinosaur dinosaur) {

        /*
         * Observation areas may contain dinosaurs
         */

        return true;
    }

    @Override
    public String toString() {

        return "ObservationEnclosure{" +
                "Nombre=' " + getName() + '\'' +
                ", Nivel de seguridad= " + securityLevel +
                ", Dinosauros= " + getDinosaurs().size() +
                '}';
    }
}