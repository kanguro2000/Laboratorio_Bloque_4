package com.arthur.dinosaurpark.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.arthur.dinosaurpark.config.ParkConfig;
import com.arthur.dinosaurpark.model.Dinosaur;
import com.arthur.dinosaurpark.model.Tourist;
import com.arthur.dinosaurpark.model.enums.DinosaurStatus;
import com.arthur.dinosaurpark.model.enums.EventType;
import com.arthur.dinosaurpark.park.Park;
import com.arthur.dinosaurpark.park.PowerPlant;
import com.arthur.dinosaurpark.park.Zone;

public class SimulationEngine {

    private static final int EVENT_RANDOM_BOUND = 100;
    private static final int MAX_EVENTS = 1000;
    private Park park;
    private int currentStep;
    private int totalSteps;
    private Random random;
    private List<SimulationEvent> events;
    private SimulationStatistics statistics;
    private boolean running;

    public SimulationEngine(Park park,int totalSteps) {

        this.park = park;
        this.totalSteps = totalSteps;
        this.currentStep = 0;
        this.random = new Random(ParkConfig.getInstance().getSeed());
        this.events = new ArrayList<>();
        this.statistics = new SimulationStatistics();
        this.running = false;
    }

    public void startSimulation() {

        running = true;
        log("\n=== INICIANDO SIMULACIÓN ===");

        while (currentStep < totalSteps&& running && !shouldStopSimulation()) {

            currentStep++;
            printStepHeader();
            processDinosaurs();
            processAggressiveDinosaurs();
            processTourists();
            processPowerConsumption();
            generateRandomEvent();
            sleepStep();
        }

        running = false;
        log("\n=== FIN DE SIMULACIÓN ===");
        printFinalStatistics();
    }

    private void printStepHeader() {
        log("\n==============================");
        log("STEP " + currentStep);
        log("==============================");
    }

    private void processDinosaurs() {

        log("\n[DINOSAURIOS]");
        for (Dinosaur dinosaur : park.getDinosaurs()) {

            double newEnergy =Math.max(0,dinosaur.getEnergy()- SimulationConstants.DINOSAUR_ENERGY_LOSS);
            dinosaur.setEnergy(newEnergy);
            updateDinosaurStatus(dinosaur);

            log(dinosaur.getName()+ " | Energía="+ dinosaur.getEnergy()+ " | Estado="+ dinosaur.getStatus());
        }
    }

    private void updateDinosaurStatus(Dinosaur dinosaur) {

        if (dinosaur.getEnergy() < 20) {

            dinosaur.setStatus(DinosaurStatus.AGGRESSIVE);

        } else if (dinosaur.getEnergy() < 50) {

            dinosaur.setStatus( DinosaurStatus.HUNGRY);

        } else {

            dinosaur.setStatus(DinosaurStatus.CALM);
        }
    }

    private void processAggressiveDinosaurs() {

        log("\n[COMPORTAMIENTO]");

        for (Dinosaur dinosaur : park.getDinosaurs()) {

            if (dinosaur.getStatus() == DinosaurStatus.AGGRESSIVE) {
                log(dinosaur.getName()+ " está agresivo");
                for (Tourist tourist : park.getTourists()) {
                    double newSatisfaction =Math.max(0,tourist.getSatisfaction() - 10);
                    tourist.setSatisfaction(newSatisfaction);
                }
                statistics.incrementAggressiveDinosaurs();
            }
        }
    }

    private void processTourists() {

        log("\n[TURISTAS]");

        for (Tourist tourist : park.getTourists()) {
            double newSatisfaction = Math.max(0,tourist.getSatisfaction() - SimulationConstants.TOURIST_SATISFACTION_LOSS);
            tourist.setSatisfaction(newSatisfaction);
            log(tourist.getName()+ " | Satisfacción=" + tourist.getSatisfaction());
        }
    }

    private void processPowerConsumption() {

        log("\n[ENERGÍA]");
        park.getZones().stream().filter(zone ->zone instanceof PowerPlant)
                .map(zone ->(PowerPlant) zone)
                .forEach(powerPlant -> {
                    powerPlant.consumeEnergy( SimulationConstants.POWER_CONSUMPTION);
                    log(powerPlant.getName()+ " | Energía restante="+ powerPlant.getEnergyLevel());
                });
    }

    private void generateRandomEvent() {

        int value = random.nextInt(EVENT_RANDOM_BOUND);
        SimulationEvent event;

        if (value < 25) {
            handleWeatherAlert();
            event = new SimulationEvent(EventType.WEATHER_ALERT, "Tormenta detectada");
            statistics.incrementWeatherAlerts();

        } else if (value < 50) {

            handleFeedingEvent();
            event = new SimulationEvent(EventType.FEEDING,"Dinosaurio alimentado");
            statistics.incrementFeedings();

        } else if (value < 75) {

            handleEscapeEvent();
            event =new SimulationEvent(EventType.ESCAPE, "Dinosaurio escapado");
            statistics.incrementEscapes();

        } else {

            handlePowerFailure();
            event = new SimulationEvent(EventType.POWER_FAILURE,"Falla de energía");
            statistics.incrementPowerFailures();
        }

        addEvent(event);
        log("\n[EVENTO]");
        log(event.toString());
    }

    private void handleWeatherAlert() {

        log("Tormenta afectando turistas");
        for (Tourist tourist : park.getTourists()) {
            double newSatisfaction = Math.max( 0,tourist.getSatisfaction() - 5);
            tourist.setSatisfaction(newSatisfaction);
        }

        Zone affectedZone = getRandomZone();
        if (affectedZone != null) {

            affectedZone.setOpen(false);
            log( affectedZone.getName() + " cerrada temporalmente por tormenta");
            statistics.incrementClosedZones();
        }
    }

    private void handleEscapeEvent() {

        if (park.getDinosaurs().isEmpty()) {
            return;
        }

        Dinosaur dinosaur = getRandomDinosaur();

        if (dinosaur == null) {
            return;
        }

        double newEnergy = Math.max(0, dinosaur.getEnergy() - SimulationConstants.ESCAPE_ENERGY_LOSS);
        dinosaur.setEnergy(newEnergy);
        dinosaur.setStatus(DinosaurStatus.AGGRESSIVE);
        log(dinosaur.getName() + " perdió energía durante escape");
        for (Tourist tourist : park.getTourists()) {
            tourist.setSatisfaction( Math.max(0,tourist.getSatisfaction() - 10));
        }
    }

    private void handleFeedingEvent() {

        if (park.getDinosaurs().isEmpty()) {
            return;
        }

        Dinosaur dinosaur = getRandomDinosaur();
        if (dinosaur == null) {
            return;
        }
        double newEnergy = Math.min(100,dinosaur.getEnergy() + SimulationConstants.FEEDING_ENERGY_GAIN);
        dinosaur.setEnergy(newEnergy);
        updateDinosaurStatus(dinosaur);
        log(dinosaur.getName() + " fue alimentado");
    }

    private void handlePowerFailure() {

        park.getZones().stream()
                .filter(zone -> zone instanceof PowerPlant)
                .map(zone -> (PowerPlant) zone)
                .forEach(powerPlant -> { powerPlant.consumeEnergy( SimulationConstants.POWER_FAILURE_DAMAGE);
                    log("Fallo eléctrico en " + powerPlant.getName());
                });

        Zone affectedZone = getRandomZone();

        if (affectedZone != null) {
            affectedZone.setOpen(false);
            log( affectedZone.getName() + " cerrada por fallo eléctrico");
            statistics.incrementClosedZones();
        }
    }

    private void addEvent( SimulationEvent event) {

        if (events.size() >= MAX_EVENTS) {
            events.remove(0);
        }

        events.add(event);
    }

    private boolean shouldStopSimulation() {
        boolean noEnergy =park.getZones().stream()
                        .filter(zone -> zone instanceof PowerPlant)
                        .map(zone ->(PowerPlant) zone)
                        .allMatch(powerPlant -> powerPlant.getEnergyLevel() <= 0);

        if (noEnergy) {
            log("\nSimulación detenida: energía agotada");
        }
        return noEnergy;
    }

    private Dinosaur getRandomDinosaur() {

        if (park.getDinosaurs().isEmpty()) {
            return null;
        }

        return park.getDinosaurs().get(random.nextInt(park.getDinosaurs().size()));
    }

    private Zone getRandomZone() {

        if (park.getZones().isEmpty()) {
            return null;
        }
        return park.getZones().get(random.nextInt(park.getZones().size()));
    }

    private void printFinalStatistics() {

        log("Eventos totales: "
                + events.size());

        log("Escapes: "
                + statistics.getEscapes());

        log("Alimentaciones: "
                + statistics.getFeedings());

        log("Alertas climáticas: "
                + statistics.getWeatherAlerts());

        log("Fallos eléctricos: "
                + statistics.getPowerFailures());

        log("Dinosaurios agresivos: "
                + statistics.getAggressiveDinosaurs());

        log("Zonas cerradas: "
                + statistics.getClosedZones());

        log("\n" + statistics);
    }

    private void sleepStep() {

        try {

            Thread.sleep(SimulationConstants.STEP_DELAY_MS);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            log("Simulación interrumpida");
        }
    }

    private void log(String message) {

        System.out.println(message);
    }

    public List<SimulationEvent> getEvents() {
        return events;
    }

    public SimulationStatistics getStatistics() {
        return statistics;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public boolean isRunning() {
        return running;
    }
}