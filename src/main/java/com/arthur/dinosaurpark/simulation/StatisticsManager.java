package com.arthur.dinosaurpark.simulation;

import com.arthur.dinosaurpark.park.Park;

public class StatisticsManager {

    private Park park;

    public StatisticsManager(Park park) {
        this.park = park;
    }

    public void printStatistics() {

        System.out.println("\n=== ESTADÍSTICAS DEL PARQUE ===");

        System.out.println("Turistas: "+ park.getTourists().size());

        System.out.println("Dinosaurios: "+ park.getDinosaurs().size());

        System.out.println("Trabajadores: "+ park.getWorkers().size());

        System.out.println("Zonas: "+ park.getZones().size());
    }
}