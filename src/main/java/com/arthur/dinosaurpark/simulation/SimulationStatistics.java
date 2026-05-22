package com.arthur.dinosaurpark.simulation;

public class SimulationStatistics {

    private int totalEvents;
    private int totalEscapes;
    private int totalFeedings;
    private int totalWeatherAlerts;
    private int totalPowerFailures;
    private int aggressiveDinosaurs;
    private int closedZones;

    public void incrementEscapes() {

        totalEscapes++;
        totalEvents++;
    }

    public void incrementFeedings() {

        totalFeedings++;
        totalEvents++;
    }

    public void incrementWeatherAlerts() {

        totalWeatherAlerts++;
        totalEvents++;
    }

    public void incrementPowerFailures() {

        totalPowerFailures++;
        totalEvents++;
    }

    public void incrementAggressiveDinosaurs() {

        aggressiveDinosaurs++;
    }

    public void incrementClosedZones() {

        closedZones++;
    }

    public int getTotalEvents() {
        return totalEvents;
    }

    public int getEscapes() {
        return totalEscapes;
    }

    public int getFeedings() {
        return totalFeedings;
    }

    public int getWeatherAlerts() {
        return totalWeatherAlerts;
    }

    public int getPowerFailures() {
        return totalPowerFailures;
    }

    public int getAggressiveDinosaurs() {
        return aggressiveDinosaurs;
    }

    public int getClosedZones() {
        return closedZones;
    }

    @Override
    public String toString() {

        return "\n=== ESTADÍSTICAS SIMULACIÓN ==="
                + "\nEventos totales: " + totalEvents
                + "\nEscapes: " + totalEscapes
                + "\nAlimentaciones: " + totalFeedings
                + "\nAlertas climáticas: " + totalWeatherAlerts
                + "\nFallos eléctricos: " + totalPowerFailures
                + "\nDinosaurios agresivos: " + aggressiveDinosaurs
                + "\nZonas cerradas: " + closedZones;
    }
}