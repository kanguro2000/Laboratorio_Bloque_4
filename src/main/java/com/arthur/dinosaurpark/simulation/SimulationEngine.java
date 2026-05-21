package com.arthur.dinosaurpark.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.arthur.dinosaurpark.model.Dinosaur;
import com.arthur.dinosaurpark.model.Tourist;
import com.arthur.dinosaurpark.model.enums.EventType;
import com.arthur.dinosaurpark.park.Park;
import com.arthur.dinosaurpark.park.PowerPlant;

public class SimulationEngine {

    private Park park;

    private int currentStep;

    private int totalSteps;

    private Random random;

    private List<SimulationEvent> events;

    public SimulationEngine(Park park,int totalSteps) {

        this.park = park;
        this.totalSteps = totalSteps;
        this.currentStep = 0;
        this.random = new Random();
        this.events = new ArrayList<>();
    }

    public void startSimulation() {

        System.out.println("\n=== INICIANDO SIMULACIÓN ===");

        while (currentStep < totalSteps) {

            currentStep++;
            System.out.println("\n==============================");

            System.out.println("STEP " + currentStep);

            System.out.println("==============================");

            processDinosaurs();
            processTourists();
            processPowerConsumption();
            generateRandomEvent();
            pauseSimulation();
        }

        System.out.println("\n=== FIN DE SIMULACIÓN ===");
    }

    private void processDinosaurs() {

        System.out.println("\n[DINOSAURIOS]");

        for (Dinosaur dinosaur : park.getDinosaurs()) {

            double newEnergy = dinosaur.getEnergy() - 2;

            if (newEnergy < 0) {
                newEnergy = 0;
            }

            dinosaur.setEnergy(newEnergy);

            System.out.println(dinosaur.getName()+ " | Energía=" + dinosaur.getEnergy());

            validateDinosaurStatus(dinosaur);
        }
    }

    private void validateDinosaurStatus(Dinosaur dinosaur) {

        if (dinosaur.getEnergy() <= 20) {

            registerEvent(EventType.FEEDING,dinosaur.getName()+ " necesita alimentación");
        }
    }

    private void processTourists() {

        System.out.println("\n[TURISTAS]");

        for (Tourist tourist : park.getTourists()) {

            double newSatisfaction =
                    tourist.getSatisfaction() - 1;

            if (newSatisfaction < 0) {
                newSatisfaction = 0;
            }

            tourist.setSatisfaction(
                    newSatisfaction);

            System.out.println(
                    tourist.getName()
                            + " | Satisfacción="
                            + tourist.getSatisfaction());

            validateTouristStatus(tourist);
        }
    }

    private void validateTouristStatus(
            Tourist tourist) {

        if (tourist.getSatisfaction() <= 30) {

            registerEvent(
                    EventType.TOURIST_INCIDENT,
                    tourist.getName()
                            + " tiene baja satisfacción");
        }
    }

    private void processPowerConsumption() {

        System.out.println(
                "\n[ENERGÍA]");

        park.getZones().stream()
                .filter(zone ->
                        zone instanceof PowerPlant)
                .map(zone ->
                        (PowerPlant) zone)
                .forEach(powerPlant -> {

                    powerPlant.consumeEnergy(5);

                    System.out.println(
                            powerPlant.getName()
                                    + " | Energía restante="
                                    + powerPlant.getEnergyLevel());

                    validatePowerPlant(powerPlant);
                });
    }

    private void validatePowerPlant(
            PowerPlant powerPlant) {

        if (powerPlant.getEnergyLevel() <= 20) {

            registerEvent(
                    EventType.POWER_FAILURE,
                    "Nivel crítico de energía en "
                            + powerPlant.getName());
        }
    }

    private void generateRandomEvent() {

        int value =
                random.nextInt(100);

        if (value < 25) {

            registerEvent(
                    EventType.WEATHER_ALERT,
                    "Tormenta detectada");
        }
        else if (value < 50) {

            registerEvent(
                    EventType.FEEDING,
                    "Dinosaurio alimentado");
        }
        else if (value < 75) {

            registerEvent(
                    EventType.ESCAPE,
                    "Dinosaurio escapado");
        }
        else {

            registerEvent(
                    EventType.POWER_FAILURE,
                    "Falla de energía en una zona");
        }
    }

    private void registerEvent(
            EventType type,
            String description) {

        SimulationEvent event =
                new SimulationEvent(
                        type,
                        description);

        events.add(event);

        System.out.println(
                "\n[EVENTO]");
        System.out.println(event);
    }

    private void pauseSimulation() {

        try {

            Thread.sleep(1500);

        } catch (InterruptedException e) {

            Thread.currentThread()
                    .interrupt();

            System.out.println(
                    "Simulación interrumpida");
        }
    }

    public List<SimulationEvent> getEvents() {
        return events;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public int getTotalSteps() {
        return totalSteps;
    }
}